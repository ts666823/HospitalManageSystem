package cn.edu.hospitalmanagesystem.controller;

import cn.edu.hospitalmanagesystem.bean.LoginBean;
import cn.edu.hospitalmanagesystem.model.DoctorEntity;
import cn.edu.hospitalmanagesystem.model.PatientEntity;
import cn.edu.hospitalmanagesystem.service.DoctorManageService;
import cn.edu.hospitalmanagesystem.service.PatientManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.dev33.satoken.util.SaResult;

import java.io.IOException;
import java.util.HashMap;


@Api(tags = "用户管理")
@RestController
@RequestMapping("user")
public class PatientManageController {
    @Autowired
    private PatientManageService patientManageService;

    @Autowired
    private DoctorManageService doctorManageService;

    @GetMapping("/login")
    @ApiOperation(value = "病人/医生登录", notes = "输入账户和密码来登录账户，返回病人ID")
    public SaResult userLogin(@RequestParam("idNumber") String idNumber, @RequestParam("password") String password) throws IOException {

        switch (patientManageService.patientLogin(idNumber, password)) {
            case Success:
                long id = patientManageService.getIdByIdNumber(idNumber);
                System.out.println(id);
                HashMap<String, Object> map = new HashMap<>();
                map.put("id",id+"");
                map.put("type",1);
                return SaResult.ok().setData(map);

            case IncorrectPassword:
                return SaResult.code(400).setMsg("Incorrect Password");
            case NoPatient:
            switch (doctorManageService.doctorLogin(idNumber, password)){
                case Success:
                    long mid = doctorManageService.getIdByIdNumber(idNumber);
                    System.out.println(mid);
                    HashMap<String, Object> mmap = new HashMap<>();
                    mmap.put("id",mid+"");
                    mmap.put("type",0);
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
    public SaResult getInfo(@RequestParam("id") Long id){
        PatientEntity patientEntity = patientManageService.getPatientEntity(id);
        HashMap<String, Object> data = new HashMap<>();

        if (patientEntity != null) {
            data.put("id", patientEntity.getId());
            data.put("sex", patientEntity.getSex());
            data.put("account_balance", patientEntity.getAccountBalance());
            data.put("name", patientEntity.getName());
            data.put("id_number", patientEntity.getIdNumber());

            return SaResult.ok().setData(data);
        }

        DoctorEntity doctorEntity = doctorManageService.getDoctorEntity(id);
        if (doctorEntity != null) {
            data.put("id", doctorEntity.getId());
            data.put("sex", doctorEntity.getSex());
            data.put("name", doctorEntity.getName());
            data.put("id_number", doctorEntity.getIdNumber());
            data.put("description", doctorEntity.getDescription());
            data.put("outpatient", doctorEntity.getOutpatient());
            data.put("price", doctorEntity.getPrice());

            return SaResult.ok().setData(data);
        }

        return SaResult.code(500).setMsg("Unknown State");

    }

    @PostMapping("/updatePatient")
    public SaResult update(@RequestBody PatientEntity patientEntity){

        return SaResult.ok().setData(patientManageService.update(patientEntity).toString());
    }

    @PostMapping("/updateDoctor")
    public SaResult update(@RequestBody DoctorEntity doctorEntity){

        return SaResult.ok().setData(doctorManageService.update(doctorEntity).toString());
    }

    @GetMapping("/recharge")
    public SaResult recharge(@RequestParam("id") Long id,@RequestParam("add") int add){

        patientManageService.add(id,add);
        return SaResult.ok();
    }
}
