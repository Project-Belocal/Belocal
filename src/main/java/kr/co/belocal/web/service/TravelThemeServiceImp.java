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
    public List<TravelThemeView> getListByMemberId(Integer id, int offset, int size) {
        return repository.findAllByMemberId(id, offset, size);
    }


//====== main화면 기본 6개 나오는 부분 =================
    @Override
    public List<TravelThemeView> getListForMain(int offset, int size) {
        return repository.getListForMain(offset,  size);
    }

//====== search-modal : 검색용 =================
//    @Override
//    public List<TravelThemeView> getListByModalInput(String query, int offset, int size){
//        return repository.getListByModalInput(query, offset, size);
//    }


//====== search-result : 값 입력 & enter =================

//====== search-result : 카테고리 아이콘 클릭시  =================
//    @Override
//    public List<TravelThemeView> getListByCtgId(Integer ctgId, int offset, int size) {
//        return repository.getListByCtgId(ctgId, offset, size);
//    }

    ////====== api/ travelTemeController=================
    @Override
    public List<TravelThemeView> getListByQuery(String query, int offset, int size){
        return repository.findAll(null, query, offset, size);
    }
    @Override
    public List<TravelThemeView> getListByCtgId(Integer ctgId, int offset, int size) {
        return repository.findAll(ctgId,null, offset, size);
    }
    @Override
    public List<TravelThemeView> getList(int offset, int size) {
        return repository.findAll(null, null, offset, size);
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

    @Override
    public Integer getCountTheme(Integer memberId) {
        return repository.getCountTheme(memberId);
    }
}//class





