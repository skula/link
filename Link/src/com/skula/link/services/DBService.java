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
	private static final String DATABASE_NAME = "linkapp.db.db";
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
		statement.bindString(7, member.getId());
		statement.bindString(8, member.getGender());
		statement.bindString(9, member.getPoints());
		statement.bindString(10, member.getBirth());
		statement.bindString(11, member.getLatitude());
		statement.bindString(12, member.getLongitude());
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
		args.put("id", member.getId());
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
		Cursor cursor = database.query(TABLE_NAME_MEMBER, new String[] { "login", "passwd", "city", "email", "status", "description", "id", "gender", "points", "birth", "latitude", "longitude"}, "id=" + id, null, null, null, null);
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
					member.setLatitude(cursor.getString(10));
					member.setLongitude(cursor.getString(11));
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
		Cursor cursor = database.query(TABLE_NAME_MEMBER, new String[] { "login", "passwd", "city", "email", "status", "description", "id", "gender", "points", "birth", "latitude", "longitude"}, null, null, null, null, null);
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

	public void insertFriendship(Friendship friendship) {
		String sql = "insert into " + TABLE_NAME_FRIENDSHIP +"(memberid, linkerid, date) values (?, ?, ?)";
		statement = database.compileStatement(sql);

		statement.bindLong(1, friendship.getMemberid());
		statement.bindLong(2, friendship.getLinkerid());
		statement.bindString(3, friendship.getDate());
		statement.executeInsert();
	}

	public void updateFriendship(int id, Friendship friendship) {
		ContentValues args = new ContentValues();
		args.put("memberid", friendship.getMemberid());
		args.put("linkerid", friendship.getLinkerid());
		args.put("date", friendship.getDate());
		database.update(TABLE_NAME_FRIENDSHIP, args, "id=" + id, null);
	}

	public void deleteFriendship(int id) {
		database.delete(TABLE_NAME_FRIENDSHIP,"id=" + id, null);
	}

	public int getNextFriendshipId() {
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
	}

	public Friendship getFriendship(int id) {
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
	}

	// public Friendship[] getFriendships() {
	public List<Friendship> getFriendships() {
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
	}

	public void insertRequest(Request request) {
		String sql = "insert into " + TABLE_NAME_REQUEST +"(id, memberid, type, label) values (?, ?, ?, ?)";
		statement = database.compileStatement(sql);

		statement.bindLong(1, request.getId());
		statement.bindLong(2, request.getMemberid());
		statement.bindLong(3, request.getType());
		statement.bindString(4, request.getLabel());
		statement.executeInsert();
	}

	public void updateRequest(int id, Request request) {
		ContentValues args = new ContentValues();
		args.put("id", request.getId());
		args.put("memberid", request.getMemberid());
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
					request.setMemberid(cursor.getString(1));
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
					request.setMemberid(cursor.getString(1));
					request.setType(cursor.getString(2));
					request.setLabel(cursor.getString(3));
					res.add(request;
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

		statement.bindLong(1, challenge.getId());
		statement.bindLong(2, challenge.getAskerId());
		statement.bindLong(3, challenge.getMakerId());
		statement.bindLong(4, challenge.getStatus());
		statement.bindString(5, challenge.getDate());
		statement.bindString(6, challenge.getLabel());
		statement.executeInsert();
	}

	public void updateChallenge(int id, Challenge challenge) {
		ContentValues args = new ContentValues();
		args.put("id", challenge.getId());
		args.put("askerId", challenge.getAskerId());
		args.put("makerId", challenge.getMakerId());
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
					challenge.setAskerId(cursor.getString(1));
					challenge.setMakerId(cursor.getString(2));
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
		Cursor cursor = database.query(TABLE_NAME_CHALLENGE, new String[] { "id", "askerId", "makerId", "status", "date", "label"}, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				try{
					Challenge challenge = new Challenge();
					challenge.setId(cursor.getString(0));
					challenge.setAskerId(cursor.getString(1));
					challenge.setMakerId(cursor.getString(2));
					challenge.setStatus(cursor.getString(3));
					challenge.setDate(cursor.getString(4));
					challenge.setLabel(cursor.getString(5));
					res.add(challenge;
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
			database.execSQL("CREATE TABLE " + TABLE_NAME_CHALLENGE + "(id INTEGER PRIMARY KEY, askerId INTEGER, makerId INTEGER, status INTEGER, date DATE, label TEXT)");
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
			database.execSQL("CREATE TABLE " + TABLE_NAME_CHALLENGE + "(id INTEGER PRIMARY KEY, askerId INTEGER, makerId INTEGER, status INTEGER, date DATE, label TEXT)");
			onCreate(database);
		}
	}
}
