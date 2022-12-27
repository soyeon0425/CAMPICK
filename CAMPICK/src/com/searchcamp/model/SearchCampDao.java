package com.searchcamp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SearchCampDao {
	
	private static SearchCampDao instance = new SearchCampDao();
	
	private SearchCampDao() {
		
	}
	
	public static SearchCampDao getInstance() {
		return instance;
	}

	private Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			connection = dataSource.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public ArrayList<SearchCampDto> getDBList(String camp_name,String donm, String sigungu,String[] camptype,String[] place,String[] thema,String[] subplace, int startRow, int pageSize) throws Exception{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ArrayList<SearchCampDto> scDtoList = new ArrayList<>();
		
		String sql = "select * from (select rownum rn , a.* from (select b.* from camp_info b where "; //sql문
		
		if(camptype != null) {
			if(sigungu != null) {
				for(int i=0; i<camptype.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND facility like '%"+camptype[i]+"%')";
					if(i+1 == camptype.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}else {
				for(int i=0; i<camptype.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%%' AND facility like '%"+camptype[i]+"%')";
					if(i+1 == camptype.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}
		}else {
			if(sigungu != null) {
				sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND facility like '%%') OR ";
			}else {
				sql += "(donm Like '%경기%' AND sigungunm like '%%' AND facility like '%%') OR ";
			}
		}
		
		if(place != null) {
			if(sigungu != null) {
				for(int i=0; i<place.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND place like '%"+place[i]+"%')";
					if(i+1 == place.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}else {
				for(int i=0; i<place.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%%' AND place like '%"+place[i]+"%')";
					if(i+1 == place.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}
		}else {
			if(sigungu != null) {
				sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND place like '%%') OR ";
			}else {
				sql += "(donm Like '%경기%' AND sigungunm like '%%' AND place like '%%') OR ";
			}
		}
		
		if(thema != null) {
			if(sigungu != null) {
				for(int i=0; i<thema.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND thema like '%"+thema[i]+"%')";
					if(i+1 == thema.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}else {
				for(int i=0; i<thema.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%%' AND thema like '%"+thema[i]+"%')";
					if(i+1 == thema.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}
		}else {
			if(sigungu != null) {
				sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND thema like '%%') AND ";
			}else {
				sql += "(donm Like '%경기%' AND sigungunm like '%%' AND thema like '%%') AND ";
			}
		}
		
		if(subplace != null) {
			if(sigungu != null) {
				for(int i=0; i<subplace.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND subplace like '%"+subplace[i]+"%')";
					if(i+1 == subplace.length) {
						break;
					}
					sql += " OR ";
				}
			}else {
				for(int i=0; i<subplace.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%%' AND thema like '%"+subplace[i]+"%')";
					if(i+1 == subplace.length) {
						break;
					}
					sql += " OR ";
				}
			}
		}else {
			if(sigungu != null) {
				sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND subplace like '%%')";
			}else {
				sql += "(donm Like '%경기%' AND sigungunm like '%%' AND subplace like '%%')";
			}
		}
		
		sql +=" )a ) where rn>=? and rn <=?";
		
		System.out.println("sql문: "+sql);
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, startRow+pageSize-1);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				SearchCampDto scDto = new SearchCampDto();
				scDto.setCamp_id(rs.getInt("camp_id"));
				scDto.setCamp_name(rs.getString("camp_name"));
				scDto.setAddr(rs.getString("addr"));
				scDto.setTel(rs.getString("tel"));
				scDto.setSubPlace(rs.getString("subplace"));
				scDto.setFacility(rs.getString("facility"));
				scDtoList.add(scDto);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				pstmt.close();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return scDtoList;
	}
	public int getDBcount(String camp_name,String donm, String sigungu,String[] camptype,String[] place,String[] thema,String[] subplace) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		SearchCampDto scDto = new SearchCampDto();
		
		String sql = "SELECT count(*) as camp_count from camp_info where ";
		if(camptype != null) {
			if(sigungu != null) {
				for(int i=0; i<camptype.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND facility like '%"+camptype[i]+"%')";
					if(i+1 == camptype.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}else {
				for(int i=0; i<camptype.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%%' AND facility like '%"+camptype[i]+"%')";
					if(i+1 == camptype.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}
		}else {
			if(sigungu != null) {
				sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND facility like '%%') OR ";
			}else {
				sql += "(donm Like '%경기%' AND sigungunm like '%%' AND facility like '%%') OR ";
			}
		}
		
		if(place != null) {
			if(sigungu != null) {
				for(int i=0; i<place.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND place like '%"+place[i]+"%')";
					if(i+1 == place.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}else {
				for(int i=0; i<place.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%%' AND place like '%"+place[i]+"%')";
					if(i+1 == place.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}
		}else {
			if(sigungu != null) {
				sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND place like '%%') OR ";
			}else {
				sql += "(donm Like '%경기%' AND sigungunm like '%%' AND place like '%%') OR ";
			}
		}
		
		if(thema != null) {
			if(sigungu != null) {
				for(int i=0; i<thema.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND thema like '%"+thema[i]+"%')";
					if(i+1 == thema.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}else {
				for(int i=0; i<thema.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%%' AND thema like '%"+thema[i]+"%')";
					if(i+1 == thema.length) {
						sql += " OR ";
						break;
					}
					sql += " OR ";
				}
			}
		}else {
			if(sigungu != null) {
				sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND thema like '%%') AND ";
			}else {
				sql += "(donm Like '%경기%' AND sigungunm like '%%' AND thema like '%%') AND ";
			}
		}
		
		if(subplace != null) {
			if(sigungu != null) {
				for(int i=0; i<subplace.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND subplace like '%"+subplace[i]+"%')";
					if(i+1 == subplace.length) {
						break;
					}
					sql += " OR ";
				}
			}else {
				for(int i=0; i<subplace.length;i++) {
					sql += "(donm Like '%경기%' AND sigungunm like '%%' AND thema like '%"+subplace[i]+"%')";
					if(i+1 == subplace.length) {
						break;
					}
					sql += " OR ";
				}
			}
		}else {
			if(sigungu != null) {
				sql += "(donm Like '%경기%' AND sigungunm like '%"+sigungu+"%' AND subplace like '%%')";
			}else {
				sql += "(donm Like '%경기%' AND sigungunm like '%%' AND subplace like '%%')";
			}
		}
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				scDto.setCamp_count(rs.getInt("camp_count"));
				rs.close();
			}
			System.out.println("dto get 카운트 : "+scDto.getCamp_count());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
			pstmt.close();
		}
		
		return scDto.getCamp_count();
	}
	public SearchCampDto getDB(int camp_id) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		SearchCampDto scDto = new SearchCampDto();
		
		String sql = "SELECT * from camp_info where camp_id ="+camp_id;
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				scDto.setCamp_name(rs.getString("camp_name"));
				scDto.setAddr(rs.getString("addr"));
				scDto.setTel(rs.getString("tel"));
				scDto.setPlace(rs.getString("place"));
				scDto.setFacility(rs.getString("facility"));
				scDto.setHomepage(rs.getString("homepage"));
				scDto.setIntro(rs.getString("intro"));
				scDto.setSubPlace(rs.getString("subPlace"));
				scDto.setLineIntro(rs.getString("lineintro"));
				scDto.setEqpmnlendcl(rs.getString("eqpmnlendcl"));
				scDto.setExprnprogrm(rs.getString("exprnprogrm"));
				scDto.setOperdate(rs.getString("operdate"));
				scDto.setSeason(rs.getString("season"));
				scDto.setThema(rs.getString("thema"));
				scDto.setPlayPlace(rs.getString("playPlace"));
				rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
			pstmt.close();
		}
		
		return scDto;
	}
}
