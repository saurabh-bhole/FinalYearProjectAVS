package avsweb;

public class ImageDTO {
	private String id;
	private String name;
	private String party;
	private String idEnc;
	
	public String getIdEnc() {
		return idEnc;
	}
	public void setIdEnc(String idEnc) {
		this.idEnc = idEnc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
}
