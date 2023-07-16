package kr.co.belocal.web.service;

import java.util.List;

import kr.co.belocal.web.entity.Category;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.TravelThemeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.repository.TravelThemeRepository;

@Service
public class TravelThemeServiceImp implements TravelThemeService {

    @Autowired
    private TravelThemeRepository repository;





//====== member-profile 페이지 용 =================
    @Override
    public List<TravelThemeView> getListByMemberId(Integer id, int offset) {
        return repository.findAll(id, offset, 6);
    }


//====== main화면 기본 6개 나오는 부분 =================
    @Override
    public List<TravelThemeView> getListForMain(int offset, int size) {
        return repository.getListForMain(offset,  size);
    }

//====== search-modal : 검색용 =================
    @Override
    public List<TravelThemeView> getListByModalInput(String query, int offset, int size){
        return repository.getListByModalInput(query, offset, size);
    }

    @Override
    public List<TravelThemeView> getListByQuery(String query){
        return repository.getListByQuery(query);
    }

    @Override
    public List<TravelThemeView> getListByCtgId(Integer offset) {
        return repository.getListByCtgId(offset);
    }
//    @Override
//    public List<TravelThemeView> getListByCtgId(Integer ctgId){
//        return repository.getListByCtgId(ctgId);




    @Override
    public List<TravelTheme> getList() {
        return null;
    }



//    @Override
//    public List<TravelTheme> getList(String categoryName, String location, String description) {
//        return null;
//    }



    @Override
    public Member getById(Integer memberId, Integer travelThemeId) {
        return null;
    }
    @Override
    public int save(TravelTheme travelTheme) {
        return repository.save(travelTheme);
    }
    @Override
    public TravelTheme get(Integer id) {
        return repository.findById(id);
    }


    @Override
    public List<TravelThemeView> getAllViewByIds(List<Integer> travelThemeIdList) {
       
        return repository.findAllViewByIds(travelThemeIdList);
    }


}//class





