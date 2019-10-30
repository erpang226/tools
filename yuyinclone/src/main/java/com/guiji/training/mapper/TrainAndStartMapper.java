package com.guiji.training.mapper;

import com.guiji.training.entity.Model;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrainAndStartMapper {

  Model querySeq(@Param("seqId")String seqId);
  void updateTrainByKey(@Param("seqId")String seqId, @Param("status") int status,@Param("trainTime") String trainTime);
  void updateByName(@Param("name")String name,@Param("status") int status);

  void updateStartParmByKey(@Param("seqId")String seqId,@Param("startName") String startName,@Param("modelName")String modelName);
  void updatePortByKey(@Param("seqId")String seqId,@Param("port") int port);

  Integer queryMaxPort();
  List<Model> queryModelInfos(@Param("status") Integer status, @Param("name") String name);

  void updateByKey(@Param("seqId")String seqId, @Param("status") int status);
  void updateStartByKey(@Param("seqId")String seqId, @Param("status") int status,@Param("startTime") String startTime,@Param("modelUrl") String modelUrl);

  List<Model>  queryModelInfo(@Param("modelName")String modelName);



}
