package cn.edu.tongji.hospitalmanagesystem.controller;

import cn.edu.tongji.hospitalmanagesystem.model.PatientEntity;
import cn.edu.tongji.hospitalmanagesystem.service.PatientManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cn.dev33.satoken.util.SaResult;

import java.io.IOException;


@Api(tags = "用户管理")
@RestController
@RequestMapping("user")
public class PatientManageController {
    @Autowired
    private PatientManageService patientManageService;

    /// TODO 加密
    @GetMapping("/login")
    @ApiOperation(value = "病人登录", notes = "输入账户和密码来登录账户，返回病人ID")
    public SaResult userLogin(@RequestParam("idNumber") String idNumber, @RequestParam("password") String password) throws IOException {
        switch (patientManageService.patientLogin(idNumber, password)) {
            case IncorrectPassword:
                return SaResult.code(400).setMsg("Incorrect Password");
            case Success:
                long id = patientManageService.getIdByIdNumber(idNumber);
                return SaResult.ok().setData(String.valueOf(id));
            case NoPatient:
                return SaResult.code(404).setMsg("No Such Patient");
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
}
