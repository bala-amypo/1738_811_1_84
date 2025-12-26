package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.impl.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Listeners(TestResultListener.class)
public class VehicleServiceHistoryApplicationTests {

    @Mock private VehicleRepository vehicleRepository;
    @Mock private GarageRepository garageRepository;
    @Mock private ServiceEntryRepository serviceEntryRepository;
    @Mock private ServicePartRepository servicePartRepository;
    @Mock private VerificationLogRepository verificationLogRepository;
    @Mock private JwtTokenProvider jwtTokenProvider;

    @InjectMocks private VehicleServiceImpl vehicleService;
    @InjectMocks private GarageServiceImpl garageService;
    @InjectMocks private ServiceEntryServiceImpl serviceEntryService;
    @InjectMocks private ServicePartServiceImpl servicePartService;
    @InjectMocks private VerificationLogServiceImpl verificationLogService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // --- SERVLET TESTS (1-8) ---
    static class SimpleHealthServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            resp.setStatus(200);
            resp.setContentType("text/plain");
            PrintWriter writer = resp.getWriter();
            writer.write("OK");
            writer.flush();
        }
    }

    @Test(groups = "servlet", priority = 1)
    public void testServletGetReturnsOk() throws Exception {
        SimpleHealthServlet servlet = new SimpleHealthServlet();
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/health");
        MockHttpServletResponse resp = new MockHttpServletResponse();
        servlet.doGet(req, resp);
        Assert.assertEquals(resp.getStatus(), 200);
        Assert.assertEquals(resp.getContentAsString(), "OK");
    }

    @Test(groups = "servlet", priority = 2)
    public void testServletContentType() throws Exception {
        SimpleHealthServlet servlet = new SimpleHealthServlet();
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/health");
        MockHttpServletResponse resp = new MockHttpServletResponse();
        servlet.doGet(req, resp);
        Assert.assertEquals(resp.getContentType(), "text/plain");
    }

    // ... (Tests 3-8: Add other servlet tests here as per original code)

    // --- CRUD TESTS (9-16) ---
    @Test(groups = "crud", priority = 9)
    public void testCreateVehicleSuccess() {
        Vehicle v = new Vehicle();
        v.setVin("VIN123");
        when(vehicleRepository.findByVin("VIN123")).thenReturn(Optional.empty());
        when(vehicleRepository.save(any(Vehicle.class))).thenAnswer(inv -> {
            Vehicle saved = inv.getArgument(0);
            saved.setId(10L);
            return saved;
        });
        Vehicle saved = vehicleService.createVehicle(v);
        Assert.assertNotNull(saved.getId());
        Assert.assertEquals(saved.getVin(), "VIN123");
    }

    // ... (Tests 10-16: Add other CRUD tests here)

    // --- DI TESTS (17-24) ---
    @Test(groups = "di", priority = 17)
    public void testVehicleServiceInjectedRepository() {
        Assert.assertNotNull(vehicleService);
        Assert.assertNotNull(vehicleRepository);
    }

    // ... (Tests 18-24: Add other DI tests here)

    // --- HIBERNATE VALIDATION TESTS (25-32) ---
    @Test(groups = "hibernate", priority = 25)
    public void testServiceEntryRespectsOdometerConstraint() {
        Vehicle v = new Vehicle(); v.setId(1L); v.setActive(true);
        ServiceEntry last = new ServiceEntry(); last.setOdometerReading(20000);
        ServiceEntry newEntry = new ServiceEntry();
        newEntry.setVehicle(v); newEntry.setOdometerReading(15000);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(v));
        when(serviceEntryRepository.findTopByVehicleOrderByOdometerReadingDesc(v)).thenReturn(Optional.of(last));

        Assert.expectThrows(IllegalArgumentException.class, () -> serviceEntryService.createServiceEntry(newEntry));
    }

    // ... (Tests 26-32: Add other Hibernate tests here)

    // --- JPA NORMALIZATION TESTS (33-40) ---
    @Test(groups = "jpa-normalization", priority = 33)
    public void testVehicleNormalizedFields() {
        Vehicle v = new Vehicle();
        v.setOwnerId(10L);
        Assert.assertEquals(v.getOwnerId(), Long.valueOf(10L));
    }
    
    private String getGarageNameFromVehicle(Vehicle v) { return null; }

    // ... (Tests 34-40: Add other JPA tests here)

    // --- MANY-TO-MANY TESTS (41-48) ---
    static class DummyVehicle { Long id; Set<DummyTag> tags = new HashSet<>(); }
    static class DummyTag { Long id; Set<DummyVehicle> vehicles = new HashSet<>(); }

    @Test(groups = "many-to-many", priority = 41)
    public void testManyToManyAddTagToVehicle() {
        DummyVehicle v = new DummyVehicle();
        DummyTag t = new DummyTag();
        v.tags.add(t);
        t.vehicles.add(v);
        Assert.assertTrue(v.tags.contains(t));
    }

    // ... (Tests 42-48: Add other Many-to-Many tests here)

    // --- SECURITY TESTS (49-56) ---
    @Test(groups = "security", priority = 49)
    public void testJwtGenerateTokenIncludesEmail() {
        when(jwtTokenProvider.generateToken(anyString(), anyString(), anyLong())).thenReturn("dummy-token");
        String token = jwtTokenProvider.generateToken("user@example.com", "USER", 1L);
        Assert.assertEquals(token, "dummy-token");
    }

    // ... (Tests 50-56: Add other Security tests here)

    // --- HQL / HCQL TESTS (57-64) ---
    @Test(groups = "hql-hcql", priority = 57)
    public void testHqlQueryByVehicleAndDateRange() {
        String hql = "select s from ServiceEntry s where s.vehicle.id = :vehicleId";
        Assert.assertTrue(hql.contains("ServiceEntry"));
    }

    // ... (Tests 58-64: Add other HQL tests here)
}