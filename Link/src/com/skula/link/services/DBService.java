package com.skula.link.services;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.skula.link.models.Challenge;
import com.skula.link.models.Member;
import com.skula.link.models.Request;

public class DBService {
	private static final String DATABASE_NAME = "linkapp.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME_MEMBER = "member";
	private static final String TABLE_NAME_FRIENDSHIP = "friendship";
	private static final String TABLE_NAME_REQUEST = "request";
	private static final String TABLE_NAME_CHALLENGE = "challenge";

	private Context context;
	private SQLiteDatabase database;
	private SQLiteStatement statement;

	public DBService(Context context) {
		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context);
		this.database = openHelper.getWritableDatabase();
	}

	public void bouchon() {
		insertMember(new Member("", "Slown", "123456", "Strasbourg", "slown@hotmail.com", "Vive les vacs!", "Blablabla", "M", "0", "02/04/1988"));
		insertMember(new Member("", "Van Kriek", "123456", "Glarwimastrauss", "van.kriek@hotmail.com", "Bolshe gu zivalich!", "bloshbloshblosh", "M", "125", "02/04/1988"));
		
		insertRequest(new Request("","1", "1", "Vends: Of Orcs And Men"));
		insertRequest(new Request("","1", "2", "Besoin de gazon synthetique"));
		
		insertChallenge(new Challenge("", "1", "2", "0", "04/09/2013", "Saute dans la fontaine"));
	}

	public void insertMember(Member member) {
		String sql = "insert into " + TABLE_NAME_MEMBER +"(login, passwd, city, email, status, description, id, gender, points, birth, latitude, longitude) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		statement = database.compileStatement(sql);

		statement.bindString(1, member.getLogin());
		statement.bindString(2, member.getPasswd());
		statement.bindString(3, member.getCity());
		statement.bindString(4, member.getEmail());
		statement.bindString(5, member.getStatus());
		statement.bindString(6, member.getDescription());
		statement.bindLong(7, getNextMemberId());
		statement.bindString(8, member.getGender());
		statement.bindString(9, member.getPoints());
		statement.bindString(10, member.getBirth());
		statement.bindDouble(11, 0.0);
		statement.bindDouble(12, 0.0);
		statement.executeInsert();
	}

	public void updateMember(int id, Member member) {
		ContentValues args = new ContentValues();
		args.put("login", member.getLogin());
		args.put("passwd", member.getPasswd());
		args.put("city", member.getCity());
		args.put("email", member.getEmail());
		args.put("status", member.getStatus());
		args.put("description", member.getDescription());
		args.put("gender", member.getGender());
		args.put("points", member.getPoints());
		args.put("birth", member.getBirth());
		database.update(TABLE_NAME_MEMBER, args, "id=" + id, null);
	}

	public void deleteMember(int id) {
		database.delete(TABLE_NAME_MEMBER,"id=" + id, null);
	}

	public int getNextMemberId() {
		Cursor cursor = database.query(TABLE_NAME_MEMBER, new String[] { "max(id)" }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				return cursor.getInt(0) + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return 1;
	}

	public Member getMember(int id) {
		Cursor cursor = database.query(TABLE_NAME_MEMBER, new String[] { "login", "passwd", "city", "email", "status", "description", "id", "gender", "points", "birth"}, "id=" + id, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				try{
					Member member = new Member();
					member.setLogin(cursor.getString(0));
					member.setPasswd(cursor.getString(1));
					member.setCity(cursor.getString(2));
					member.setEmail(cursor.getString(3));
					member.setStatus(cursor.getString(4));
					member.setDescription(cursor.getString(5));
					member.setId(cursor.getString(6));
					member.setGender(cursor.getString(7));
					member.setPoints(cursor.getString(8));
					member.setBirth(cursor.getString(9));
					return member;
				}catch(Exception e){
					String s = e.getMessage();
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return null;
	}

	// public Member[] getMembers() {
	public List<Member> getMembers() {
		List<Member> res = new ArrayList<Member>();
		Cursor cursor = database.query(TABLE_NAME_MEMBER, new String[] { "login", "passwd", "city", "email", "status", "description", "id", "gender", "points", "birth"}, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				try{
					Member member = new Member();
					member.setLogin(cursor.getString(0));
					member.setPasswd(cursor.getString(1));
					member.setCity(cursor.getString(2));
					member.setEmail(cursor.getString(3));
					member.setStatus(cursor.getString(4));
					member.setDescription(cursor.getString(5));
					member.setId(cursor.getString(6));
					member.setGender(cursor.getString(7));
					member.setPoints(cursor.getString(8));
					member.setBirth(cursor.getString(9));
					res.add(member);
				}catch(Exception e){
					String s = e.getMessage();
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		// return (Member[]) res.toArray(new Member[res.size()]);
		return res;
	}
	
	public int getMemberId(String login){
		return 1;
	}
	
	public String getMemberLogin(String id){
		return "";
	}

	/*public void insertFriendship(Friendship friendship) {
		String sql = "insert into " + TABLE_NAME_FRIENDSHIP +"(memberid, linkerid, date) values (?, ?, ?)";
		statement = database.compileStatement(sql);

		statement.bindLong(1, friendship.getMemberid());
		statement.bindLong(2, friendship.getLinkerid());
		statement.bindString(3, friendship.getDate());
		statement.executeInsert();
	}*/

	/*public void updateFriendship(int id, Friendship friendship) {
		ContentValues args = new ContentValues();
		args.put("memberid", friendship.getMemberid());
		args.put("linkerid", friendship.getLinkerid());
		args.put("date", friendship.getDate());
		database.update(TABLE_NAME_FRIENDSHIP, args, "id=" + id, null);
	}*/

	/*public void deleteFriendship(int id) {
		database.delete(TABLE_NAME_FRIENDSHIP,"id=" + id, null);
	}*/

	/*public int getNextFriendshipId() {
		Cursor cursor = database.query(TABLE_NAME_FRIENDSHIP, new String[] { "max(id)" }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				return cursor.getInt(0) + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return 1;
	}*/

	/*public Friendship getFriendship(int id) {
		Cursor cursor = database.query(TABLE_NAME_FRIENDSHIP, new String[] { "memberid", "linkerid", "date"}, "id=" + id, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				try{
					Friendship friendship = new Friendship();
					friendship.setMemberid(cursor.getString(0));
					friendship.setLinkerid(cursor.getString(1));
					friendship.setDate(cursor.getString(2));
					return friendship;
				}catch(Exception e){
					String s = e.getMessage();
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return null;
	}*/

	// public Friendship[] getFriendships() {
	/*public List<Friendship> getFriendships() {
		List<Friendship> res = new ArrayList<Friendship>();
		Cursor cursor = database.query(TABLE_NAME_FRIENDSHIP, new String[] { "memberid", "linkerid", "date"}, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				try{
					Friendship friendship = new Friendship();
					friendship.setMemberid(cursor.getString(0));
					friendship.setLinkerid(cursor.getString(1));
					friendship.setDate(cursor.getString(2));
					res.add(friendship;
				}catch(Exception e){
					String s = e.getMessage();
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		// return (Friendship[]) res.toArray(new Friendship[res.size()]);
		return res;
	}*/

	public void insertRequest(Request request) {
		String sql = "insert into " + TABLE_NAME_REQUEST +"(id, memberid, type, label) values (?, ?, ?, ?)";
		statement = database.compileStatement(sql);

		statement.bindLong(1, getNextRequestId());
		statement.bindLong(2, Long.valueOf(request.getMemberId()));
		statement.bindLong(3, Long.valueOf(request.getType()));
		statement.bindString(4, request.getLabel());
		statement.executeInsert();
	}

	public void updateRequest(int id, Request request) {
		ContentValues args = new ContentValues();
		args.put("memberid", request.getMemberId());
		args.put("type", request.getType());
		args.put("label", request.getLabel());
		database.update(TABLE_NAME_REQUEST, args, "id=" + id, null);
	}

	public void deleteRequest(int id) {
		database.delete(TABLE_NAME_REQUEST,"id=" + id, null);
	}

	public int getNextRequestId() {
		Cursor cursor = database.query(TABLE_NAME_REQUEST, new String[] { "max(id)" }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				return cursor.getInt(0) + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return 1;
	}

	public Request getRequest(int id) {
		Cursor cursor = database.query(TABLE_NAME_REQUEST, new String[] { "id", "memberid", "type", "label"}, "id=" + id, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				try{
					Request request = new Request();
					request.setId(cursor.getString(0));
					request.setMemberId(cursor.getString(1));
					request.setType(cursor.getString(2));
					request.setLabel(cursor.getString(3));
					return request;
				}catch(Exception e){
					String s = e.getMessage();
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return null;
	}

	// public Request[] getRequests() {
	public List<Request> getRequests() {
		List<Request> res = new ArrayList<Request>();
		Cursor cursor = database.query(TABLE_NAME_REQUEST, new String[] { "id", "memberid", "type", "label"}, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				try{
					Request request = new Request();
					request.setId(cursor.getString(0));
					request.setMemberId(cursor.getString(1));
					request.setType(cursor.getString(2));
					request.setLabel(cursor.getString(3));
					res.add(request);
				}catch(Exception e){
					String s = e.getMessage();
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		// return (Request[]) res.toArray(new Request[res.size()]);
		return res;
	}

	public void insertChallenge(Challenge challenge) {
		String sql = "insert into " + TABLE_NAME_CHALLENGE +"(id, askerId, makerId, status, date, label) values (?, ?, ?, ?, ?, ?)";
		statement = database.compileStatement(sql);

		statement.bindLong(1, getNextChallengeId());
		statement.bindLong(2, Long.valueOf(getMemberId(challenge.getAskerLogin())));
		statement.bindLong(3, Long.valueOf(getMemberId(challenge.getMakerLogin())));
		statement.bindLong(4, Long.valueOf(challenge.getStatus()));
		statement.bindString(5, challenge.getDate());
		statement.bindString(6, challenge.getLabel());
		statement.executeInsert();
	}

	public void updateChallenge(int id, Challenge challenge) {
		ContentValues args = new ContentValues();
		args.put("askerId", getMemberId(challenge.getAskerLogin()));
		args.put("makerId", getMemberId(challenge.getMakerLogin()));
		args.put("status", challenge.getStatus());
		args.put("date", challenge.getDate());
		args.put("label", challenge.getLabel());
		database.update(TABLE_NAME_CHALLENGE, args, "id=" + id, null);
	}

	public void deleteChallenge(int id) {
		database.delete(TABLE_NAME_CHALLENGE,"id=" + id, null);
	}

	public int getNextChallengeId() {
		Cursor cursor = database.query(TABLE_NAME_CHALLENGE, new String[] { "max(id)" }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				return cursor.getInt(0) + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return 1;
	}

	public Challenge getChallenge(int id) {
		Cursor cursor = database.query(TABLE_NAME_CHALLENGE, new String[] { "id", "askerId", "makerId", "status", "date", "label"}, "id=" + id, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				try{
					Challenge challenge = new Challenge();
					challenge.setId(cursor.getString(0));
					challenge.setAskerLogin(getMemberLogin(cursor.getString(1)));
					challenge.setMakerLogin(getMemberLogin(cursor.getString(2)));
					challenge.setStatus(cursor.getString(3));
					challenge.setDate(cursor.getString(4));
					challenge.setLabel(cursor.getString(5));
					return challenge;
				}catch(Exception e){
					String s = e.getMessage();
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return null;
	}

	// public Challenge[] getChallenges() {
	public List<Challenge> getChallenges() {
		List<Challenge> res = new ArrayList<Challenge>();
		Cursor cursor = database.query(TABLE_NAME_CHALLENGE, new String[] { "id", "askerid", "makerid", "status", "date", "label"}, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				try{
					Challenge challenge = new Challenge();
					challenge.setId(cursor.getString(0));
					challenge.setAskerLogin(getMemberLogin(cursor.getString(1)));
					challenge.setMakerLogin(getMemberLogin(cursor.getString(2)));
					challenge.setStatus(cursor.getString(3));
					challenge.setDate(cursor.getString(4));
					challenge.setLabel(cursor.getString(5));
					res.add(challenge);
				}catch(Exception e){
					String s = e.getMessage();
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		// return (Challenge[]) res.toArray(new Challenge[res.size()]);
		return res;
	}
	
	// public Challenge[] getChallenges() {
	public List<Challenge> getChallenges(String memberId) {
		List<Challenge> res = new ArrayList<Challenge>();
		Cursor cursor = database.query(TABLE_NAME_CHALLENGE, new String[] { "id", "askerid", "makerid", "status", "date", "label"}, "askerid=" + memberId + " or makerid=" + memberId, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				try{
					Challenge challenge = new Challenge();
					challenge.setId(cursor.getString(0));
					challenge.setAskerLogin(getMemberLogin(cursor.getString(1)));
					challenge.setMakerLogin(getMemberLogin(cursor.getString(2)));
					challenge.setStatus(cursor.getString(3));
					challenge.setDate(cursor.getString(4));
					challenge.setLabel(cursor.getString(5));
					res.add(challenge);
				}catch(Exception e){
					String s = e.getMessage();
				}

			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		// return (Challenge[]) res.toArray(new Challenge[res.size()]);
		return res;
	}

	private static class OpenHelper extends SQLiteOpenHelper {
		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase database) {
			database.execSQL("CREATE TABLE " + TABLE_NAME_MEMBER + "(login TEXT, passwd TEXT, city TEXT, email TEXT, status TEXT, description TEXT, id INTEGER PRIMARY KEY, gender INTEGER, points INTEGER, birth DATE, latitude DOUBLE, longitude DOUBLE)");
			database.execSQL("CREATE TABLE " + TABLE_NAME_FRIENDSHIP + "(memberid INTEGER, linkerid INTEGER, date DATE)");
			database.execSQL("CREATE TABLE " + TABLE_NAME_REQUEST + "(id INTEGER PRIMARY KEY, memberid INTEGER, type INTEGER, label TEXT)");
			database.execSQL("CREATE TABLE " + TABLE_NAME_CHALLENGE + "(id INTEGER PRIMARY KEY, askerid INTEGER, makerid INTEGER, status INTEGER, date DATE, label TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
			database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEMBER);
			database.execSQL("CREATE TABLE " + TABLE_NAME_MEMBER + "(login TEXT, passwd TEXT, city TEXT, email TEXT, status TEXT, description TEXT, id INTEGER PRIMARY KEY, gender INTEGER, points INTEGER, birth DATE, latitude DOUBLE, longitude DOUBLE)");
			database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FRIENDSHIP);
			database.execSQL("CREATE TABLE " + TABLE_NAME_FRIENDSHIP + "(memberid INTEGER, linkerid INTEGER, date DATE)");
			database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REQUEST);
			database.execSQL("CREATE TABLE " + TABLE_NAME_REQUEST + "(id INTEGER PRIMARY KEY, memberid INTEGER, type INTEGER, label TEXT)");
			database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CHALLENGE);
			database.execSQL("CREATE TABLE " + TABLE_NAME_CHALLENGE + "(id INTEGER PRIMARY KEY, askerid INTEGER, makerid INTEGER, status INTEGER, date DATE, label TEXT)");
			onCreate(database);
		}
	}
}
