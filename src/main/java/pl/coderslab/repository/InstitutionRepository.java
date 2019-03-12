package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Institution;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution,Long> {

    List<Institution> findInstitutionByNameContainingOrMissionTargetContainingOrNeedsContainingOrAddressContainingOrEmailContainingOrPhoneContainingOrLocationContaining(String name, String missionTarget,String needs,String address,String email,String phone,String location);

            //name,missionTarget,needs,address,email,phone,location
}
