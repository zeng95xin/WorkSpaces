package com.bola.nwcl.web.controller.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bola.nwcl.biz.AdminManager;
import com.bola.nwcl.common.exception.BusinessDealException;
import com.bola.nwcl.common.exception.BusinessValidateException;
import com.bola.nwcl.common.exception.PatternException;
import com.bola.nwcl.common.result.BaseResult;
import com.bola.nwcl.common.util.DateUtils;
import com.bola.nwcl.dal.enums.CodeEnum;
import com.bola.nwcl.dal.mybatis.mapper.ActivityMapper;
import com.bola.nwcl.dal.mybatis.mapper.AnnouncementMapper;
import com.bola.nwcl.dal.mybatis.mapper.ArticleReleaseMapper;
import com.bola.nwcl.dal.mybatis.mapper.BizUserRoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.BuildingMapper;
import com.bola.nwcl.dal.mybatis.mapper.CommunityMapper;
import com.bola.nwcl.dal.mybatis.mapper.DoorRecordMapper;
import com.bola.nwcl.dal.mybatis.mapper.HousekeeperUserMessageMapper;
import com.bola.nwcl.dal.mybatis.mapper.LikeShareMapper;
import com.bola.nwcl.dal.mybatis.mapper.MaintenanceMapper;
import com.bola.nwcl.dal.mybatis.mapper.NeighborHelpMapper;
import com.bola.nwcl.dal.mybatis.mapper.OwnersMarketMapper;
import com.bola.nwcl.dal.mybatis.mapper.PayOrderMapper;
import com.bola.nwcl.dal.mybatis.mapper.RoomMapper;
import com.bola.nwcl.dal.mybatis.mapper.UserOpinionMapper;
import com.bola.nwcl.dal.mybatis.mapper.VisitorsMapper;
import com.bola.nwcl.dal.mybatis.model.ActivityExample;
import com.bola.nwcl.dal.mybatis.model.Admin;
import com.bola.nwcl.dal.mybatis.model.AnnouncementExample;
import com.bola.nwcl.dal.mybatis.model.ArticleReleaseExample;
import com.bola.nwcl.dal.mybatis.model.BizUserRoomExample;
import com.bola.nwcl.dal.mybatis.model.Building;
import com.bola.nwcl.dal.mybatis.model.BuildingExample;
import com.bola.nwcl.dal.mybatis.model.Community;
import com.bola.nwcl.dal.mybatis.model.DoorRecordExample;
import com.bola.nwcl.dal.mybatis.model.HousekeeperUserMessageExample;
import com.bola.nwcl.dal.mybatis.model.LikeShareExample;
import com.bola.nwcl.dal.mybatis.model.MaintenanceExample;
import com.bola.nwcl.dal.mybatis.model.NeighborHelpExample;
import com.bola.nwcl.dal.mybatis.model.OwnersMarketExample;
import com.bola.nwcl.dal.mybatis.model.PayOrder;
import com.bola.nwcl.dal.mybatis.model.PayOrderExample;
import com.bola.nwcl.dal.mybatis.model.Room;
import com.bola.nwcl.dal.mybatis.model.RoomExample;
import com.bola.nwcl.dal.mybatis.model.UserOpinionExample;
import com.bola.nwcl.dal.mybatis.model.VisitorsExample;
import com.bola.nwcl.web.listener.SessionListener;
import com.bola.nwcl.web.model.SessionInfo;

@Controller
@RequestMapping(value = "web/admin")
public class TAdminRefreshController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired private AdminManager adminManager;
	@Autowired private DoorRecordMapper doorRecordMapper;
	@Autowired private PayOrderMapper payOrderMapper;
	@Autowired private MaintenanceMapper maintenanceMapper;
	@Autowired private VisitorsMapper visitorsMapper;
	@Autowired private ArticleReleaseMapper articleReleaseMapper;
	@Autowired private HousekeeperUserMessageMapper housekeeperUserMessageMapper;
	@Autowired private UserOpinionMapper userOpinionMapper;
	@Autowired private ActivityMapper activityMapper;
	@Autowired private AnnouncementMapper announcementMapper;
	@Autowired private LikeShareMapper likeShareMapper;
	@Autowired private NeighborHelpMapper neighborHelpMapper;
	@Autowired private OwnersMarketMapper ownersMarketMapper;
	@Autowired private CommunityMapper communityMapper;
	@Autowired private BuildingMapper buildingMapper;
	@Autowired private RoomMapper roomMapper;
	@Autowired private BizUserRoomMapper bizUserRoomMapper;
	
	
	@ResponseBody
	@RequestMapping(value="/getRefresh",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public BaseResult getRefresh(HttpSession session, HttpServletRequest request) {
		BaseResult baseResult=new BaseResult("FAILUE", "登录成功");
//		BaseResult baseResult=new BaseResult("SUCCESS", "登录成功");
		try {
			if(null != session.getAttribute("isrefresh")){
				baseResult.setCode("SUCCESS");
				session.removeAttribute("isrefresh");
				return baseResult;
			}
			SessionInfo sessionInfo=(SessionInfo)session.getAttribute("sessionInfo");
			if(null == sessionInfo){
				return baseResult;
			}
			Admin admin = adminManager.selectByPrimaryKey(sessionInfo.getId());
			if(null == admin){
				return baseResult;
			}
			if(null == admin.getRefreshStatus()){
				return baseResult;
			}
			if(1 == admin.getRefreshStatus()){
				SessionListener.addRefreshEvent(sessionInfo.getId(), admin.getRefreshTime());
				admin.setRefreshStatus(0);
				adminManager.updateByPrimaryKeySelective(admin);
				baseResult.setCode("SUCCESS");
				return baseResult;
			}
		} catch (PatternException e) {
			logger.info("参数校验失败,{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.PATTERN_ERROR.getCode(),
					e.getMessage());
		} catch (BusinessValidateException e) {
			logger.info("业务验证异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseResult(
					CodeEnum.BUSSINESS_VALIDATE_ERROR.getCode(), e.getMessage());
		} catch (BusinessDealException e) {
			logger.info("业务处理异常， 错误信息：{}", e.getMessage());
			baseResult = new BaseResult(
					CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统异常，{}", e.getMessage());
			baseResult = new BaseResult(CodeEnum.SYSTEM_ERROR.getCode(), "系统异常");
		}
		logger.info("：{}", baseResult.toString());
		return baseResult;
	}
	
	@RequestMapping(value = "/exportStatistics")
	public void exportStatistics(HttpServletRequest request, HttpServletResponse response, String start, String end) throws IOException {
		
		Date startTime = DateUtils.strToDate(start, DateUtils.YYYY_MM_DD_HH_MM_SS);
		Date endTime = DateUtils.strToDate(end, DateUtils.YYYY_MM_DD_HH_MM_SS);
		
		
		System.out.println("进入了export");
		
		SimpleDateFormat sdf_filename = new SimpleDateFormat("MM-dd");
		String fileName = "统计数据" + sdf_filename.format(new Date());
		String codedFileName;
		codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		
		response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
		ServletOutputStream out = response.getOutputStream();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(fileName);
		
		HSSFRow temp_row = sheet.createRow(0);
		HSSFCellStyle style_title = workbook.createCellStyle();
		style_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		String time_region_str = "--";
		boolean region = false;
		if(null != startTime && null != endTime){
			time_region_str = sdf_filename.format(startTime) + sdf_filename.format(endTime);
			region = true;
		}
		
		String[][] columns = new String[][]{
			new String[]{"名称","9000"},
			new String[]{"使用次数","5000"},
			new String[]{"时间范围","5000"}
		};
		
		for (int i = 0; i < columns.length; i++) {
			HSSFCell cell = temp_row.createCell(i);
			cell.setCellValue(columns[i][0]);
			cell.setCellStyle(style_title);
			sheet.setColumnWidth(i, Integer.valueOf(columns[i][1]));
		}
		logger.debug("开始导出数据");
		for (int i = 1; i < 12; i++) {
			String values[] = getValues(i, time_region_str, startTime, endTime, region);
			
			HSSFRow row = sheet.createRow(i);
			
			for (int j = 0; j < values.length; j++) {
				HSSFCell cell = row.createCell(j);
				if(j == 1){
					cell.setCellValue(Integer.valueOf(values[j]));
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				}else{
					cell.setCellValue(values[j]);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				}
			}
		}
		
		HSSFRow temp_row_2 = sheet.createRow(13);
		
		String[][] columns_2 = new String[][]{
			new String[]{"楼栋","9000"},
			new String[]{"已注册房号（按照房间统计）","5000"},
			new String[]{"注册业主","5000"},
			new String[]{"注册家属","5000"},
			new String[]{"注册租客","5000"}
		};
		
		for (int i = 0; i < columns_2.length; i++) {
			HSSFCell cell = temp_row_2.createCell(i);
			cell.setCellValue(columns[i][0]);
			cell.setCellStyle(style_title);
			if(i>2){
				sheet.setColumnWidth(i, Integer.valueOf(columns[i][1]));
			}
		}
		int index = 14;
		List<Community> communitys = communityMapper.selectByExample(null);
		for(Community c : communitys){
			BuildingExample example_building = new BuildingExample();
			example_building.or().andCommunityIdEqualTo(c.getId());
			List<Building> buildings = buildingMapper.selectByExample(example_building);
			for(Building b : buildings){
				HSSFRow temp_row_3 = sheet.createRow(index);
				index ++;
				RoomExample example_room = new RoomExample();
				example_room.or().andBuildingIdEqualTo(b.getId());
				int roomCount = roomMapper.countByExample(example_room);
				List<Room> rooms = roomMapper.selectByExample(example_room);
				List<Long> room_ids = new ArrayList<Long>(rooms.size());
				for(Room r : rooms){
					room_ids.add(r.getId());
				}
				BizUserRoomExample example_buser_room1 = new BizUserRoomExample();
				BizUserRoomExample example_buser_room2 = new BizUserRoomExample();
				BizUserRoomExample example_buser_room3 = new BizUserRoomExample();
				example_buser_room1.or().andRoomIdIn(room_ids).andTypeEqualTo("业主");
				example_buser_room2.or().andRoomIdIn(room_ids).andTypeEqualTo("家属");
				example_buser_room3.or().andRoomIdIn(room_ids).andTypeEqualTo("租客");
				int count1 = bizUserRoomMapper.countByExample(example_buser_room1);
				int count2 = bizUserRoomMapper.countByExample(example_buser_room2);
				int count3 = bizUserRoomMapper.countByExample(example_buser_room3);
				
				HSSFCell cell0 = temp_row_3.createCell(0);
				cell0.setCellValue(b.getName());
				cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
				
				HSSFCell cell1 = temp_row_3.createCell(1);
				cell1.setCellValue(roomCount);
				cell1.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				
				HSSFCell cell2 = temp_row_3.createCell(2);
				cell2.setCellValue(count1);
				cell2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				
				HSSFCell cell3 = temp_row_3.createCell(3);
				cell3.setCellValue(count2);
				cell3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				
				HSSFCell cell4 = temp_row_3.createCell(4);
				cell4.setCellValue(count3);
				cell4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				
			}
		}
		
		
		
		logger.debug("导出数据结束");
		try {
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String[] getValues(int i, String time_region_str, Date start, Date end, boolean region){
		String[] values = null;
		String description = "";
		String times = "";
		if(11 == i){
			values = new String[]{"小区业主注册情况"};
			return values;
		}
		else if(1 == i){
			description = "智能门禁使用次数";
			DoorRecordExample example_record = new DoorRecordExample();
			if(region){
				example_record.or().andRowAddTimeBetween(start, end);
			}
			times = doorRecordMapper.countByExample(example_record) + "";
		}
		else if(2 == i){
			description = "在线缴费次数及金额";
			PayOrderExample example = new PayOrderExample();
			if(region){
				example.or().andRawAddTimeBetween(start, end).andPayStatusEqualTo("ALREADY_PAY");
			}else{
				example.or().andPayStatusEqualTo("ALREADY_PAY");
			}
			times = payOrderMapper.countByExample(example) + "/";
			List<PayOrder> orders = payOrderMapper.selectByExample(example);
			int totalMoney = 0;
			for(PayOrder p : orders){
				totalMoney += p.getPayMoney();
			}
			times += totalMoney/100d;
		}
		else if(3 == i){
			description = "在线报修";
			MaintenanceExample example = new MaintenanceExample();
			if(region){
				example.or().andRowAddTimeBetween(start, end);
			}
			times = maintenanceMapper.countByExample(example) + "";
		}
		else if(4 == i){
			description = "访客来访";
			VisitorsExample example = new VisitorsExample();
			if(region){
				example.or().andRowAddTimeBetween(start, end);
			}
			times = visitorsMapper.countByExample(example) + "";
		}
		else if(5 == i){
			description = "物品放行";
			ArticleReleaseExample example = new ArticleReleaseExample();
			if(region){
				example.or().andRowAddTimeBetween(start, end);
			}
			times = articleReleaseMapper.countByExample(example) + "";
		}
		else if(6 == i){
			description = "在线管家";
			HousekeeperUserMessageExample example = new HousekeeperUserMessageExample();
			if(region){
				example.or().andRowAddTimeBetween(start, end);
			}
			times = housekeeperUserMessageMapper.countByExample(example) + "";
		}
		else if(7 == i){
			description = "服务建议";
			UserOpinionExample example = new UserOpinionExample();
			if(region){
				example.or().andRowAddTimeBetween(start, end);
			}
			times = userOpinionMapper.countByExample(example) + "";
		}
		else if(8 == i){
			description = "社区文化";
			ActivityExample example = new ActivityExample();
			if(region){
				example.or().andRowAddTimeBetween(start, end);
			}
			times = activityMapper.countByExample(example) + "";
		}
		else if(9 == i){
			description = "社区公告";
			AnnouncementExample example = new AnnouncementExample();
			if(region){
				example.or().andRowAddTimeBetween(start, end);
			}
			times = announcementMapper.countByExample(example) + "";
		}
		else if(10 == i){
			description = "邻里圈";
			LikeShareExample example_l = new LikeShareExample();
			NeighborHelpExample example_n = new NeighborHelpExample();
			OwnersMarketExample example_o = new OwnersMarketExample();
			if(region){
				example_l.or().andRowAddTimeBetween(start, end);
				example_n.or().andRowAddTimeBetween(start, end);
				example_o.or().andRowAddTimeBetween(start, end);
			}
			times = (likeShareMapper.countByExample(example_l) + neighborHelpMapper.countByExample(example_n) + ownersMarketMapper.countByExample(example_o)) + "";
		}
		else if(12 == i){
			description = "邻里圈";
			LikeShareExample example_l = new LikeShareExample();
			NeighborHelpExample example_n = new NeighborHelpExample();
			OwnersMarketExample example_o = new OwnersMarketExample();
			if(region){
				example_l.or().andRowAddTimeBetween(start, end);
				example_n.or().andRowAddTimeBetween(start, end);
				example_o.or().andRowAddTimeBetween(start, end);
			}
			times = (likeShareMapper.countByExample(example_l) + neighborHelpMapper.countByExample(example_n) + ownersMarketMapper.countByExample(example_o)) + "";
		}
		values = new String[]{description, times, time_region_str};
		return values;
	}
	
}
