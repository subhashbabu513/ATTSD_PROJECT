/**
 * Created by: nuwan_r
 * Created on: 6/18/2021
 **/
package com.attsw.attsw_exam;

import com.attsw.attsw_exam.enums.Status;
import com.attsw.attsw_exam.model.Student;
import com.attsw.attsw_exam.model.Teacher;
import com.attsw.attsw_exam.repository.StudentRepository;
import com.attsw.attsw_exam.repository.TeacherRepository;
import com.attsw.attsw_exam.service.StudentService;
import com.attsw.attsw_exam.service.TeacherService;
import com.attsw.attsw_exam.utility.DateAuditingProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = AttswExamApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class TeacherFindAllControllerCustomResponseTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DateAuditingProvider dateAuditingProvider;

    @Test
    public void findTeacherByIdControlerTest() throws Exception {

        Teacher teacher3 = new Teacher();
        teacher3.setId(99);
        teacher3.setStatus(Status.ACTIVE.getStatusSeq());
        teacher3.setAddress("Panjab");
        teacher3.setContactNo("0672728882");
        teacher3.setEmail("shewagv1@gmail.com");
        teacher3.setName("shewag");

        Gson gson = new Gson();
        String jsonStringTeacher = gson.toJson(teacher3);

        when(teacherRepository.findByIdAndStatus(teacher3.getId(),Status.ACTIVE.getStatusSeq())).thenReturn(java.util.Optional.ofNullable(teacher3));

        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStringTeacher))
                .andExpect(status().isOk());

        Mockito.verify(teacherRepository).findByIdAndStatus(teacher3.getId(),teacher3.getStatus());
    }

    @Test
    public void testFinalAllActiveDifControlerTest() throws Exception {

        Teacher teacher3 = new Teacher();
        teacher3.setId(99);
        teacher3.setStatus(Status.DELETED.getStatusSeq());
        teacher3.setAddress("Panjab");
        teacher3.setContactNo("0672728882");
        teacher3.setEmail("shewagv1@gmail.com");
        teacher3.setName("shewag");

        Teacher teacher2 = new Teacher();
        teacher3.setId(100);
        teacher3.setStatus(Status.DELETED.getStatusSeq());
        teacher3.setAddress("Maharasht");
        teacher3.setContactNo("0464383883");
        teacher3.setEmail("mahen@gmail.com");
        teacher3.setName("mahen");

        List<Teacher> listOFDeleletedTeachers = new ArrayList<>();
        listOFDeleletedTeachers.add(teacher3);
        listOFDeleletedTeachers.add(teacher2);

        Mockito.when(teacherRepository.findAllByStatus(Status.DELETED.getStatusSeq())).thenReturn(listOFDeleletedTeachers);

        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/findAllActive")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isNotFound());

		/*MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/teacher/findAllActive")
						.accept(MediaType.APPLICATION_JSON)
		).andReturn();*/

        Mockito.verify(teacherRepository).findAllByStatus(Status.ACTIVE.getStatusSeq());

    }

    @Test
    public void testFinalAllActiveReturnOkControlerTest() throws Exception {

        Teacher teacher3 = new Teacher();
        teacher3.setId(99);
        teacher3.setStatus(Status.ACTIVE.getStatusSeq());
        teacher3.setAddress("Panjab");
        teacher3.setContactNo("0672728882");
        teacher3.setEmail("shewagv1@gmail.com");
        teacher3.setName("shewag");

        Teacher teacher2 = new Teacher();
        teacher3.setId(100);
        teacher3.setStatus(Status.ACTIVE.getStatusSeq());
        teacher3.setAddress("Maharasht");
        teacher3.setContactNo("0464383883");
        teacher3.setEmail("mahen@gmail.com");
        teacher3.setName("mahen");

        List<Teacher> listOFActiveTeachers = new ArrayList<>();
        listOFActiveTeachers.add(teacher3);
        listOFActiveTeachers.add(teacher2);

        Mockito.when(teacherRepository.findAllByStatus(Status.ACTIVE.getStatusSeq())).thenReturn(listOFActiveTeachers);

        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/findAllActive")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isOk());

		/*MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/teacher/findAllActive")
						.accept(MediaType.APPLICATION_JSON)
		).andReturn();*/

        Mockito.verify(teacherRepository).findAllByStatus(Status.ACTIVE.getStatusSeq());

    }


}
