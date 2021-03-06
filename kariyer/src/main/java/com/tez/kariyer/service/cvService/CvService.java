package com.tez.kariyer.service.cvService;

import com.tez.kariyer.dto.*;
import com.tez.kariyer.model.entity.*;
import com.tez.kariyer.model.entity.address.Il;
import com.tez.kariyer.model.entity.address.Ulke;
import com.tez.kariyer.model.entity.parameterTable.DriverLicense;
import com.tez.kariyer.model.entity.parameterTable.Position;
import com.tez.kariyer.model.entity.parameterTable.WayOfWork;
import com.tez.kariyer.model.repository.*;
import com.tez.kariyer.model.repository.addressRepository.CityRepository;
import com.tez.kariyer.model.repository.addressRepository.CountryRepository;
import com.tez.kariyer.model.repository.addressRepository.DistrictRepository;
import com.tez.kariyer.model.repository.parameterTableRepository.*;
import com.tez.kariyer.response.ResponseItem;
import com.tez.kariyer.security.SessionInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CvService {

    @Autowired
    protected CommunicationInfoRepository communicationInfoRepository;
    @Autowired
    protected UserPrivateInfoRepository userPrivateInfoRepository;
    @Autowired
    protected SummaryInfoRepository summaryInfoRepository;
    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    protected WorkExperienceRepository workExperienceRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected PositionRepository positionRepository;
    @Autowired
    protected CompanySectorRepository companySectorRepository;
    @Autowired
    protected WayOfWorkRepository wayOfWorkRepository;
    @Autowired
    protected DistrictRepository districtRepository;
    @Autowired
    protected SoldierStatusRepository soldierStatusRepository;
    @Autowired
    protected CountryRepository countryRepository;
    @Autowired
    protected DriverLicenseRepository driverLicenseRepository;
    @Autowired
    protected EducationInfoRepository educationInfoRepository;
    @Autowired
    protected CityRepository cityRepository;

    public ResponseItem saveCommunicationInfo(CommunicationInfoDTO communicationInfoDTO){
        ResponseItem responseItem = new ResponseItem();
        CommunicationInfo communicationInfo = new CommunicationInfo();
        User user = SessionInfo.getInstance().getUser();
        try {
            communicationInfoDTO.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse(communicationInfoDTO.getBirthDateStr()));
            communicationInfo = modelMapper.map(communicationInfoDTO, CommunicationInfo.class);
            communicationInfo.setIlce(districtRepository.findByIdd(communicationInfoDTO.getIlce()));
            communicationInfo.setUser(user);
            responseItem.setResult(true);
            responseItem.setMessage("İşlem Başarılı!");
            communicationInfoRepository.save(communicationInfo);
        }catch (Exception e){
            responseItem.setResult(false);
            responseItem.setMessage("İşlem Başarısız!");
            e.printStackTrace();
        }
        return responseItem;
    }
    public ResponseItem saveUserPrivateInfo(UserPrivateInfoDTO userPrivateInfoDTO){
        ResponseItem responseItem = new ResponseItem();
        User user = SessionInfo.getInstance().getUser();

        try {
            DriverLicense driverLicense = driverLicenseRepository.findByIdd(userPrivateInfoDTO.getDriverLicense());
            UserPrivateInfo userPrivateInfo = modelMapper.map(userPrivateInfoDTO, UserPrivateInfo.class);
            userPrivateInfo.setId(userPrivateInfoDTO.getId());
            userPrivateInfo.setUlke(countryRepository.findByIdd(userPrivateInfoDTO.getUlke()));
            userPrivateInfo.setSoldierStatus(soldierStatusRepository.findByIdd(userPrivateInfoDTO.getSoldierStatus()));
            userPrivateInfo.setDriverLicense(driverLicense.getLicence());
            userPrivateInfo.setUser(user);
            responseItem.setResult(true);
            responseItem.setMessage("İşlem Başarılı!");
            userPrivateInfoRepository.save(userPrivateInfo);
        }catch (Exception e){
            responseItem.setResult(false);
            responseItem.setMessage("İşlem Başarısız!");
            e.printStackTrace();
        }
        return responseItem;
    }
    public ResponseItem saveSummaryInfo(SummaryInfoDTO summaryInfo){
        ResponseItem responseItem = new ResponseItem();
        User user = SessionInfo.getInstance().getUser();
        try {
            SummaryInfo summaryInfo1 = new SummaryInfo();
            responseItem.setResult(true);
            responseItem.setMessage("İşlem Başarılı!");
            if (summaryInfo.getId()!=0){
                summaryInfo1.setId(summaryInfo.getId());
            }
            summaryInfo1.setUser(user);
            summaryInfo1.setTarget(summaryInfo.getTarget());
            summaryInfoRepository.save(summaryInfo1);
        }catch (Exception e){
            responseItem.setResult(false);
            responseItem.setMessage("İşlem Başarısız!");
            e.printStackTrace();
        }
        return responseItem;
    }
    public ResponseItem saveWorkExperience(WorkExperienceDTO workExperienceDTO){
        ResponseItem responseItem = new ResponseItem();
        User user = SessionInfo.getInstance().getUser();
        WorkExperience workExperience = new WorkExperience();
        try {
            workExperience.setUser(user);
            workExperience.setPosition(positionRepository.findByIdd(workExperienceDTO.getPosition()));
            workExperience.setCompanySector(companySectorRepository.findByIdd(workExperienceDTO.getCompanySector()));
            workExperience.setWayOfWork(wayOfWorkRepository.findByIdd(workExperienceDTO.getWayOfWork()));
            workExperience.setIlce(districtRepository.findByIdd(workExperienceDTO.getDistrict()));
            workExperience.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse(workExperienceDTO.getStartDate()));
            if (workExperienceDTO.getFinishDate()!=null && !workExperienceDTO.getFinishDate().equals("")) {
                workExperience.setFinishDate(new SimpleDateFormat("dd/MM/yyyy").parse(workExperienceDTO.getFinishDate()));
            }
            workExperience.setFirmsName(workExperienceDTO.getFirmsName());
            workExperience.setWeb(workExperienceDTO.getWeb());
            responseItem.setResult(true);
            responseItem.setMessage("İşlem Başarılı!");
            workExperienceRepository.save(workExperience);
        }catch (Exception e){
            responseItem.setResult(false);
            responseItem.setMessage("İşlem Başarısız!");
            e.printStackTrace();
        }
        return responseItem;
    }
    public ResponseItem saveEducation(EducationInfoDTO educationInfoDTO){
        ResponseItem responseItem = new ResponseItem();
        User user = SessionInfo.getInstance().getUser();
        try {
            EducationInfo educationInfo = modelMapper.map(educationInfoDTO,EducationInfo.class);
            educationInfo.setUser(user);
            if (educationInfoDTO.getId()!=0){
                educationInfo.setId(educationInfoDTO.getId());
            }
            Il city =cityRepository.findById(educationInfoDTO.getIl()).get();
            educationInfo.setCity(city);
            educationInfoRepository.save(educationInfo);
            responseItem.setResult(true);
            responseItem.setMessage("İşlem Başarılı!");
        }catch (Exception e){
            responseItem.setResult(false);
            responseItem.setMessage("İşlem Başarısız!");
            e.printStackTrace();
        }
        return responseItem;
    }
}
