package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.model.Institution;
import pl.coderslab.model.User;
import pl.coderslab.repository.InstitutionRepository;

import java.util.List;

@Service
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    public void save(Institution institution) {
        institutionRepository.save(institution);
    }

    public void update(Institution institution) {
        institutionRepository.save(institution);
    }

    public void deleteById(Long id) {
        institutionRepository.delete(id);
    }

    public void deleteInstitution(Institution institution) {
        institutionRepository.delete(institution);
    }

    public Institution findById(Long id) {
        return institutionRepository.findOne(id);
    }

    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    // search
    public List<Institution> searchInstitution(String search) {
        return institutionRepository.findInstitutionByNameContainingOrMissionTargetContainingOrNeedsContainingOrAddressContainingOrEmailContainingOrPhoneContainingOrLocationContaining(search,search,search,search,search,search,search);
    }
}
