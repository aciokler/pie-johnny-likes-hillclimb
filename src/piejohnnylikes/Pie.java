package piejohnnylikes;

import algorithm.hillclimbsearch.InputElement;

public class Pie implements InputElement<Boolean> {

	private Shapes shape;
	private CrustSizes crustSize;
	private CrustShades crustShade;
	private FillingSizes fillingSize;
	private FillingShades fillingShade;

	private boolean pieJohnnyLikes = false;

	public Pie(Shapes shape, CrustSizes crustSize, CrustShades crustShade, FillingSizes fillingSize,
			FillingShades fillingShade, boolean pieJohnnyLikes) {
		this.shape = shape;
		this.crustSize = crustSize;
		this.crustShade = crustShade;
		this.fillingSize = fillingSize;
		this.fillingShade = fillingShade;
		this.pieJohnnyLikes = pieJohnnyLikes;
	}

	@Override
	public Boolean getInputClass() {
		return pieJohnnyLikes;
	}

	public Shapes getShape() {
		return shape;
	}

	public void setShape(Shapes shape) {
		this.shape = shape;
	}

	public CrustSizes getCrustSize() {
		return crustSize;
	}

	public void setCrustSize(CrustSizes crustSize) {
		this.crustSize = crustSize;
	}

	public CrustShades getCrustShade() {
		return crustShade;
	}

	public void setCrustShade(CrustShades crustShade) {
		this.crustShade = crustShade;
	}

	public FillingSizes getFillingSize() {
		return fillingSize;
	}

	public void setFillingSize(FillingSizes fillingSize) {
		this.fillingSize = fillingSize;
	}

	public FillingShades getFillingShade() {
		return fillingShade;
	}

	public void setFillingShade(FillingShades fillingShade) {
		this.fillingShade = fillingShade;
	}

	public interface PieAttribute {

	}

	public enum Shapes implements PieAttribute {
		CIRCLE, TRIANGLE, SQUARE;
	}

	public enum CrustSizes implements PieAttribute {
		Thick, Thin;
	}

	public enum CrustShades implements PieAttribute {
		Gray, White, Dark;
	}

	public enum FillingSizes implements PieAttribute {
		Thick, Thin;
	}

	public enum FillingShades implements PieAttribute {
		Gray, White, Dark;
	}
}
