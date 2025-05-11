package com.example.javaailangchain4j.tool;

import com.example.javaailangchain4j.entity.Appointment;
import com.example.javaailangchain4j.service.AppointmentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTools {

    @Autowired
    private AppointmentService appointmentService;

    @Tool(name = "预约医生",value = "根据参数，先执行工具方法queryDepartment查询是否可预约，并直接给用户回答是否可预约，并让用户确认所有预约信息，用户确认后再进行预约。")
    public String bookAppointment(Appointment appointment) {
        System.out.println("预约医生"+appointment);
        Appointment appointmentDB = appointmentService.getOne(appointment);
        if (appointmentDB == null) {
            appointment.setId(null);
            if(appointmentService.save(appointment)) {
                return "预约成功,并返回预约详情";
            } else {
                return "预约失败";
            }
        }
        return "您在相同的时间已经预约过该科室的医生了";
    }

    @Tool(name = "取消预约挂号",value = "根据参数，查询预约是否存在，如果存在则删除预约记录并返回取消预约成功，否则返回取消预约失败。")
    public String cancelAppointment(Appointment appointment) {
        Appointment appointmentDB = appointmentService.getOne(appointment);
        if (appointmentDB != null) {
            if(appointmentService.removeById(appointmentDB.getId())) {
                return "取消预约成功";
            } else {
                return "取消预约失败";
            }
        }
        return "取消预约失败，您没有预约该科室的医生";
    }

    @Tool(name = "查询是否有号源",value = "根据科室名称，日期，时间和医生查询是否有号源，并返回给用户")
    public boolean queryDepartment(@P(value = "科室名称") String department, @P(value = "日期") String date, @P(value = "时间，可选值") String time, @P(value = "医生名称",required = false) String doctorName) {
        System.out.println("查询是否有号源"+department + date + time + doctorName);
        System.out.println("查询是否有号源");
        System.out.println("科室名称：" + department);
        System.out.println("日期：" + date);
        System.out.println("时间：" + time);
        System.out.println("医生名称：" + doctorName);
        return true;
    }
}
