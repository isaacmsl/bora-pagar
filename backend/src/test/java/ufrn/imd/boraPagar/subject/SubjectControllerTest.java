package ufrn.imd.boraPagar.subject;


import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

    @Test
    public void shouldFindAllByModality() throws Exception {
        SubjectModalityType desire = SubjectModalityType.IN_PERSON;
        List<SubjectModel> expected = new ArrayList<>();
        expected.add(new SubjectModel());
        
        Mockito.when(subjectService.findAllByModality(desire))
            .thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("/subjects?modality={modality}", String.valueOf(desire)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldFindByCode() throws Exception {
        String desireCode = "1"; 
        SubjectModel expected = new SubjectModel();

        Mockito.when(subjectService.findByCode(desireCode)).thenReturn(Optional.of(expected));

        mockMvc.perform(MockMvcRequestBuilders.get("/subjects?code={code}", desireCode))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldFindByComponentID() throws Exception {
        String desireComponentId = "1"; 
        SubjectModel expected = new SubjectModel();

        Mockito.when(subjectService.findByComponentID(desireComponentId)).thenReturn(Optional.of(expected));

        mockMvc.perform(MockMvcRequestBuilders.get("/subjects?componentID={componentID}", desireComponentId))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldAddInterestedUserByComponentID() throws Exception {
        String credential = "credential";
        String desireComponentId = "1";
        SubjectModel expected = new SubjectModel();

        Mockito.when(subjectService.addInterestedUserByComponentID(credential, desireComponentId)).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.post("/subjects/interested", desireComponentId)
                    .header(credential, credential)
                    .param("componentID", desireComponentId))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldRemoveInterestedUserByComponentID() throws Exception {
        String credential = "credential";
        String desireComponentId = "1";
        SubjectModel expected = new SubjectModel();

        Mockito.when(subjectService.removeInterestedUserByComponentID(credential, desireComponentId)).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.delete("/subjects/interested", desireComponentId)
                    .header(credential, credential)
                    .param("componentID", desireComponentId))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
