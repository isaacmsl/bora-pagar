package ufrn.imd.boraPagar.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufrn.imd.boraPagar.repositories.SubjectRepository;

@Service
public class SubjectService {
    
    @Autowired
    SubjectRepository subjectRepository;

    public SubjectModel findById(Integer id) {
        SubjectModel obj = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Object not found!"));
        return obj;
    }

    public SubjectModel findByName(String name) {
        SubjectModel obj = subjectRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Object not found!"));
        return obj;
    }

    public SubjectModel findByCode(String code) {
        SubjectModel obj = subjectRepository.findByCode(code).orElseThrow(() -> new ResourceNotFoundException("Object not found!"));
        return obj;
    }

    public List<SubjectModel> findAllByHour(Integer hour) {
        return subjectRepository.findAllByHour(hour);
    }

    public List<SubjectModel> findAllByDepartment(String department) {
        return subjectRepository.findAllByDepartment(department);
    }

    public List<SubjectModel> findAll() {
        return articleRepository.findAll();
    }

    public SubjectModel save(SubjectModel obj) {
        return articleRepository.save(obj);;
    }

    public SubjectModel update(Integer id, SubjectModel obj) {}

    public void delete(Integer id) {
        SubjectModel obj = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Object not found!"));

        articleRepository.delete(obj);
    }

}
