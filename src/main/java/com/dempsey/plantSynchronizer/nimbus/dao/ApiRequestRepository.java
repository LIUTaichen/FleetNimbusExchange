package com.dempsey.plantSynchronizer.nimbus.dao;

import com.dempsey.plantSynchronizer.nimbus.entity.ApiRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ApiRequestRepository extends CrudRepository<ApiRequest, Integer> {
    List<ApiRequest> findAll();
    List<ApiRequest> findAllByDatasetAndTimestampAfter(String dataset, Date timestamp);
//    List<ApiRequest> findAllByDatasetAndTimestampAfterAndEditorAssetItemID(String dataset, Date timestamp, Integer editorAssetItemID);
//    List<ApiRequest> findAllByDatasetAndTimestampAfterAndEditorAssetItemIDAndJobIdIsNotNull(String dataset, Date timestamp, Integer editorAssetItemID);
    ApiRequest findTopByJobIdIsNotNullAndDatasetAndAssetFleetIdOrderByTimestampDesc(String dataset, String plantID);
    List<ApiRequest> findAllByJobIdIsNotNullAndDatasetAndAssetFleetIdOrderByTimestampDesc(String dataset, String plantID);
}
