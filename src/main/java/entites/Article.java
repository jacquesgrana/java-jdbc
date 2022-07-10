package entites;

public class Article {
	
	private Integer id;
	private String ref;
	private String design;
	private Float prix;
	private Fournisseur fou;
	
	
	/**
	 * @param id
	 * @param ref
	 * @param design
	 * @param prix
	 * @param fou
	 */
	public Article(Integer id, String ref, String design, Float prix, Fournisseur fou) {
		super();
		this.id = id;
		this.ref = ref;
		this.design = design;
		this.prix = prix;
		this.fou = fou;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id : ");
		builder.append(id);
		builder.append(" /  ref : ");
		builder.append(ref);
		builder.append(" / design : ");
		builder.append(design);
		builder.append(" / prix : ");
		builder.append(prix);
		builder.append(" / fou : ");
		builder.append(fou.toString());
		return builder.toString();
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}


	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}


	/**
	 * @return the design
	 */
	public String getDesign() {
		return design;
	}


	/**
	 * @param design the design to set
	 */
	public void setDesign(String design) {
		this.design = design;
	}


	/**
	 * @return the prix
	 */
	public Float getPrix() {
		return prix;
	}


	/**
	 * @param prix the prix to set
	 */
	public void setPrix(Float prix) {
		this.prix = prix;
	}


	/**
	 * @return the fou
	 */
	public Fournisseur getFou() {
		return fou;
	}


	/**
	 * @param fou the fou to set
	 */
	public void setFou(Fournisseur fou) {
		this.fou = fou;
	}

}
