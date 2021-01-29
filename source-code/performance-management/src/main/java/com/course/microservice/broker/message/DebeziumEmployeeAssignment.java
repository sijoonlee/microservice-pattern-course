package com.course.microservice.broker.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DebeziumEmployeeAssignment {

	public static class EmployeeAssignment {
		private long assignment_id;
		private long date_end;
		private long date_start;
		private String employee_id;
		private String position;

		public long getAssignment_id() {
			return assignment_id;
		}

		public long getDate_end() {
			return date_end;
		}

		public long getDate_start() {
			return date_start;
		}

		public String getEmployee_id() {
			return employee_id;
		}

		public String getPosition() {
			return position;
		}

		public void setAssignment_id(long assignment_id) {
			this.assignment_id = assignment_id;
		}

		public void setDate_end(long date_end) {
			this.date_end = date_end;
		}

		public void setDate_start(long date_start) {
			this.date_start = date_start;
		}

		public void setEmployee_id(String employee_id) {
			this.employee_id = employee_id;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		@Override
		public String toString() {
			return "EmployeeAssignment [assignment_id=" + assignment_id + ", date_end=" + date_end + ", date_start="
					+ date_start + ", employee_id=" + employee_id + ", position=" + position + "]";
		}
	}

	public static class Payload {
		private EmployeeAssignment after;
		private EmployeeAssignment before;
		private String op;
		private Source source;
		private long ts_ms;

		public EmployeeAssignment getAfter() {
			return after;
		}

		public EmployeeAssignment getBefore() {
			return before;
		}

		public String getOp() {
			return op;
		}

		public Source getSource() {
			return source;
		}

		public long getTs_ms() {
			return ts_ms;
		}

		public void setAfter(EmployeeAssignment after) {
			this.after = after;
		}

		public void setBefore(EmployeeAssignment before) {
			this.before = before;
		}

		public void setOp(String op) {
			this.op = op;
		}

		public void setSource(Source source) {
			this.source = source;
		}

		public void setTs_ms(long ts_ms) {
			this.ts_ms = ts_ms;
		}

		@Override
		public String toString() {
			return "Payload [after=" + after + ", before=" + before + ", op=" + op + ", source=" + source + ", ts_ms="
					+ ts_ms + "]";
		}
	}

	public static class Source {
		private String connector;
		private String db;
		private String file;
		private String gtid = null;
		private String name;
		private long pos;
		private String query = null;
		private long row;
		private long server_id;
		private String snapshot;
		private String table;
		private long thread;
		private long ts_ms;
		private String version;

		public String getConnector() {
			return connector;
		}

		public String getDb() {
			return db;
		}

		public String getFile() {
			return file;
		}

		public String getGtid() {
			return gtid;
		}

		public String getName() {
			return name;
		}

		public long getPos() {
			return pos;
		}

		public String getQuery() {
			return query;
		}

		public long getRow() {
			return row;
		}

		public long getServer_id() {
			return server_id;
		}

		public String getSnapshot() {
			return snapshot;
		}

		public String getTable() {
			return table;
		}

		public long getThread() {
			return thread;
		}

		public long getTs_ms() {
			return ts_ms;
		}

		public String getVersion() {
			return version;
		}

		public void setConnector(String connector) {
			this.connector = connector;
		}

		public void setDb(String db) {
			this.db = db;
		}

		public void setFile(String file) {
			this.file = file;
		}

		public void setGtid(String gtid) {
			this.gtid = gtid;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setPos(long pos) {
			this.pos = pos;
		}

		public void setQuery(String query) {
			this.query = query;
		}

		public void setRow(long row) {
			this.row = row;
		}

		public void setServer_id(long server_id) {
			this.server_id = server_id;
		}

		public void setSnapshot(String snapshot) {
			this.snapshot = snapshot;
		}

		public void setTable(String table) {
			this.table = table;
		}

		public void setThread(long thread) {
			this.thread = thread;
		}

		public void setTs_ms(long ts_ms) {
			this.ts_ms = ts_ms;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		@Override
		public String toString() {
			return "Source [connector=" + connector + ", db=" + db + ", file=" + file + ", gtid=" + gtid + ", name="
					+ name + ", pos=" + pos + ", query=" + query + ", row=" + row + ", server_id=" + server_id
					+ ", snapshot=" + snapshot + ", table=" + table + ", thread=" + thread + ", ts_ms=" + ts_ms
					+ ", version=" + version + "]";
		}
	}

	private Payload payload;

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

}
