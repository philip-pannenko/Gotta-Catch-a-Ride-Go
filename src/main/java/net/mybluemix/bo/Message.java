package net.mybluemix.bo;

public class Message {

	private String status;

	public Message(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Message [status=" + status + "]";
	}

}
