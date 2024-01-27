package ufrn.imd.boraPagar.subject;


import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(SubjectController.class)
public class SubjectControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @Test
    public void shouldFindAllByTotalHours() throws Exception {
        int desireHour = 1;
        List<SubjectModel> expected = new ArrayList<>();
        expected.add(new SubjectModel());
        
        Mockito.when(subjectService.findAllByTotalHours(desireHour))
            .thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("/subjects?totalHours={totalHours}", String.valueOf(desireHour)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
