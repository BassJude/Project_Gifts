package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.model.Gift;
import pl.coderslab.model.Institution;
import pl.coderslab.model.User;
import pl.coderslab.repository.GiftRepository;

import java.util.List;

@Service
public class GiftService {

    @Autowired
    private GiftRepository giftRepository;

    public void save(Gift gift) {
        giftRepository.save(gift);
    }

    public void update(Gift gift) {
        giftRepository.save(gift);
    }

    public void deleteById(Long id) {
        giftRepository.delete(id);
    }

    public void delete(Gift gift) {
        giftRepository.delete(gift);
    }

    public Gift findById(Long id) {
        return giftRepository.findOne(id);
    }

    public List<Gift> findAll() {
        return giftRepository.findAll();
    }


    public List<Gift> findUserGifts(User user) {
        return giftRepository.findAllByUser(user);
    }

    public boolean canItBeDeleted(User user) {
        if (findUserGifts(user).size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Gift> hasInstitutionGifts(Institution institution) {return giftRepository.findAllByInstitution(institution);}

    public boolean canInstitutionBeDeleted(Institution institution) {
        return hasInstitutionGifts(institution).size()==0;


    }
}
