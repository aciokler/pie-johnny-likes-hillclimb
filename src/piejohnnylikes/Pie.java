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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (obj instanceof Pie) {
			Pie pie = (Pie) obj;
			return this.getCrustShade() == pie.getCrustShade() && this.getCrustSize() == pie.getCrustSize()
					&& this.getShape() == pie.getShape() && this.getFillingShade() == pie.getFillingShade()
					&& this.getFillingSize() == pie.getFillingSize();
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.getCrustShade().hashCode() + this.getCrustSize().hashCode() + this.getFillingShade().hashCode()
				+ this.getFillingSize().hashCode() + this.getShape().hashCode();
	}

	public interface PieAttribute {

	}

	public enum Shapes implements PieAttribute {
		CIRCLE {
			@Override
			public String toString() {
				return "Shape == Circle";
			}
		},
		TRIANGLE {
			@Override
			public String toString() {
				return "Shape == Triangle";
			}
		},
		SQUARE {
			@Override
			public String toString() {
				return "Shape == Square";
			}
		};
	}

	public enum CrustSizes implements PieAttribute {
		Thick {
			@Override
			public String toString() {
				return "CrustSize == Thick";
			}
		},
		Thin {
			@Override
			public String toString() {
				return "CrustSize == Thin";
			}
		};
	}

	public enum CrustShades implements PieAttribute {
		Gray {
			@Override
			public String toString() {
				return "CrustShade == Gray";
			}
		},
		White {
			@Override
			public String toString() {
				return "CrustShade == White";
			}
		},
		Dark {
			@Override
			public String toString() {
				return "CurstShade == Dark";
			}
		};
	}

	public enum FillingSizes implements PieAttribute {
		Thick {
			@Override
			public String toString() {
				return "FillingSize == Thick";
			}
		},
		Thin {
			@Override
			public String toString() {
				return "FillingSize == Thin";
			}
		};
	}

	public enum FillingShades implements PieAttribute {
		Gray {
			@Override
			public String toString() {
				return "FillingShade == Gray";
			}
		},
		White {
			@Override
			public String toString() {
				return "FillingShade == White";
			}
		},
		Dark {
			@Override
			public String toString() {
				return "FillingShade == Dark";
			}
		};
	}
}
