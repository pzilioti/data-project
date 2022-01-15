package dev.zilioti.daofab.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zilioti.daofab.entity.Child;
import dev.zilioti.daofab.entity.Parent;
import dev.zilioti.daofab.repository.ChildRepository;
import dev.zilioti.daofab.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataService {

    @Autowired
    ParentRepository parentRepository;

    @Autowired
    ChildRepository childRepository;

    public Iterable<Parent> saveParents(List<Parent> parents) {
        return parentRepository.saveAll(parents);
    }

    public Iterable<Child> saveChildren(List<Child> children){
        return childRepository.saveAll(children);
    }

    public Map<String, Object> getParents(Integer page, String sort){
        Sort sorted;
        if(sort.equals("asc")){
            sorted = Sort.by("id").ascending();
        }else{
            sorted = Sort.by("id").descending();
        }
        Pageable sortedById = PageRequest.of(page, 2, sorted);

        Page<Parent> parents = parentRepository.findAll(sortedById);

        //sets the total paid amount for each parent
        parents.forEach(p -> p.setTotalPaidAmount(childRepository.getSumPaidAmountByParentId(p.getId())));

        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(parents, Map.class);

        return map;
    }

    public List<Map<String, Object>> getChildren(){
        return childRepository.getChildrenByParent();
    }

}
