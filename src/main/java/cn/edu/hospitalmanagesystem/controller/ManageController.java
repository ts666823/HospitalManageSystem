package cn.edu.hospitalmanagesystem.controller;

import cn.edu.hospitalmanagesystem.bean.OrderBean;
import cn.edu.hospitalmanagesystem.model.DoctorEntity;
import cn.edu.hospitalmanagesystem.model.MedicineEntity;
import cn.edu.hospitalmanagesystem.model.PatientEntity;
import cn.edu.hospitalmanagesystem.model.RecommendEntity;
import cn.edu.hospitalmanagesystem.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.dev33.satoken.util.SaResult;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "用户管理")
@RestController
@RequestMapping("user")
public class ManageController {
    @Autowired
    private PatientManageService patientManageService;

    @Autowired
    private DoctorManageService doctorManageService;


    @Autowired
    private AppointmentManageService appointmentManageService;

    @Autowired
    private MedicineManageService medicineManageService;

    @Autowired
    private RecommendManageService recommendManageService;

    @GetMapping("/login")
    @ApiOperation(value = "病人/医生登录", notes = "输入账户和密码来登录账户，返回病人ID")
    public SaResult userLogin(@RequestParam("idNumber") String idNumber, @RequestParam("password") String password) throws IOException {

        switch (patientManageService.patientLogin(idNumber, password)) {
            case Success:
                long id = patientManageService.getIdByIdNumber(idNumber);
                System.out.println(id);
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", id + "");
                map.put("type", 1);
                return SaResult.ok().setData(map);

            case IncorrectPassword:
                return SaResult.code(400).setMsg("Incorrect Password");
            case NoPatient:
                switch (doctorManageService.doctorLogin(idNumber, password)) {
                    case Success:
                        long mid = doctorManageService.getIdByIdNumber(idNumber);
                        System.out.println(mid);
                        HashMap<String, Object> mmap = new HashMap<>();
                        mmap.put("id", mid + "");
                        mmap.put("type", 0);
                        return SaResult.ok().setData(mmap);

                    case IncorrectPassword:
                        return SaResult.code(400).setMsg("Incorrect Password");
                    case NoDoctor:
                        return SaResult.code(404).setMsg("No Such Person");
                }
        }
        return SaResult.code(500).setMsg("Unknown State");
    }


    @PostMapping("/register")
    @ApiOperation(value = "病人注册", notes = "输入病人相关信息注册，返回病人ID")
    public SaResult signUpPatient(@RequestBody PatientEntity patientEntity) {
        switch (patientManageService.patientSignup(patientEntity)) {
            case Successful:
                long id = patientManageService.getIdByIdNumber(patientEntity.getIdNumber());
                return SaResult.ok().setData(String.valueOf(id));
            case IdNumberExisted:
                return SaResult.code(400).setMsg("ID Number Existed");
        }
        return SaResult.code(500).setMsg("Unknown State");
    }

    @GetMapping("/getInfo")
    public SaResult getInfo(@RequestParam("id") Long id) {
        PatientEntity patientEntity = patientManageService.getPatientEntity(id);
        HashMap<String, Object> data = new HashMap<>();

        if (patientEntity != null) {
            data.put("id", patientEntity.getId());
            data.put("sex", patientEntity.getSex());
            data.put("accountBalance", patientEntity.getAccountBalance());
            data.put("name", patientEntity.getName());
            data.put("idNumber", patientEntity.getIdNumber());
            data.put("type", 1);

            return SaResult.ok().setData(data);
        }

        DoctorEntity doctorEntity = doctorManageService.getDoctorEntity(id);
        if (doctorEntity != null) {
            data.put("id", doctorEntity.getId());
            data.put("sex", doctorEntity.getSex());
            data.put("name", doctorEntity.getName());
            data.put("idNumber", doctorEntity.getIdNumber());
            data.put("description", doctorEntity.getDescription());
            data.put("outpatient", doctorEntity.getOutpatient());
            data.put("image",doctorEntity.getImage());
            data.put("type", 0);

            return SaResult.ok().setData(data);
        }

        return SaResult.code(500).setMsg("Unknown State");

    }

    @PostMapping("/updatePatient")
    public SaResult update(@RequestBody PatientEntity patientEntity) {

        return SaResult.ok().setData(patientManageService.update(patientEntity).toString());
    }

    @PostMapping("/updateDoctor")
    public SaResult update(@RequestBody DoctorEntity doctorEntity) {

        return SaResult.ok().setData(doctorManageService.update(doctorEntity).toString());
    }

    @GetMapping("/recharge")
    public SaResult recharge(@RequestParam("id") Long id, @RequestParam("add") int add) {

        patientManageService.add(id, add);
        return SaResult.ok();
    }

    @GetMapping("/order")
    public SaResult order(@RequestBody OrderBean orderBean) {

        Timestamp timestamp = new Timestamp(orderBean.getTime());
        appointmentManageService.order(orderBean.getPatientId(), orderBean.getDoctorId(), timestamp);
        return SaResult.ok();
    }

    @GetMapping("/updateOrder")
    public SaResult order(@RequestParam("id") Long id, @RequestParam("status") int status) {
        appointmentManageService.updateOrder(id, status);
        return SaResult.ok();
    }

    @GetMapping("/getOrder")
    public SaResult getOrder(@RequestParam("id") Long id) {
        return SaResult.ok().setData(appointmentManageService.getOrder(id));
    }

    @GetMapping("/getDoctor")
    public SaResult getDoctor(@RequestParam("outpatient") String outpatient) {
        List<DoctorEntity> doctors = doctorManageService.getDoctors(outpatient);
        List<Map<String, Object>> result = new ArrayList<>();
        for (DoctorEntity doctorEntity : doctors) {
            Map<String, Object> map = new HashMap<>();
            map.put("doctorId", doctorEntity.getId());
            map.put("sex", doctorEntity.getSex());
            map.put("description", doctorEntity.getDescription());
            map.put("name", doctorEntity.getName());
            map.put("price", doctorEntity.getPrice());
            map.put("outpatient", doctorEntity.getOutpatient());
            map.put("image", doctorEntity.getImage());
            result.add(map);
        }
        return SaResult.ok().setData(result);
    }

    @GetMapping("/addDoctor")
    public SaResult addDoctor(@RequestParam("password") String password, @RequestParam("description") String description, @RequestParam("idNumber") String idNumber, @RequestParam("sex") String sex, @RequestParam("outpatient") String outpatient, @RequestParam("price") int price, @RequestParam("image") String image ,@RequestParam("name") String name) {
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setPassword(password);
        doctorEntity.setDescription(description);
        doctorEntity.setIdNumber(idNumber);
        doctorEntity.setSex(sex);
        doctorEntity.setOutpatient(outpatient);
        doctorEntity.setPrice(price);
        doctorEntity.setImage(image);
        doctorEntity.setName(name);
        return SaResult.ok().setData(doctorManageService.addDoctor(doctorEntity));
    }

    @GetMapping("/addMedicine")
    public SaResult addMedicine(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("image") String image) {
        MedicineEntity medicineEntity = new MedicineEntity();
        medicineEntity.setDescription(description);
        medicineEntity.setName(name);
        medicineEntity.setImage(image);
        return SaResult.ok().setData(medicineManageService.addMedicine(medicineEntity));
    }

    @GetMapping("/getMedicine")
    public SaResult getMedicine() {
        return SaResult.ok().setData(medicineManageService.getMedicine());
    }

    @GetMapping("/getRecommendMedicine")
    public SaResult getRecommendMedicine(@RequestParam("id") long id) {
      return SaResult.ok().setData(recommendManageService.getRecommendMedicine(id));
    }


    @GetMapping("/recommendMedicine")
    public SaResult recommendMedicine(@RequestParam("patientId") long patientId,@RequestParam("doctorId") long doctorId,@RequestParam("medicineId") long medicineId,@RequestParam("appointmentId") long appointmentId) {
        RecommendEntity recommendEntity = new RecommendEntity();
        recommendEntity.setMedicineId(medicineId);
        recommendEntity.setDoctorId(doctorId);
        recommendEntity.setPatientId(patientId);
        recommendEntity.setAppointmentId(appointmentId);
        return SaResult.ok().setData(recommendManageService.recommend(recommendEntity));
    }
}
