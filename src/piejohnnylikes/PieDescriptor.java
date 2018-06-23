package piejohnnylikes;

import piejohnnylikes.Pie.PieAttribute;

public class PieDescriptor implements PieDescriptorExpression {

	private boolean negateExpression = false;
	private PieAttribute pieAttribute;

	public PieDescriptor(PieAttribute pieAttribute) {
		this.pieAttribute = pieAttribute;
		this.negateExpression = false;
	}

	public PieDescriptor(PieAttribute pieAttribute, boolean negateExpresion) {
		this(pieAttribute);
		this.negateExpression = negateExpresion;
	}

	public PieDescriptor(PieDescriptor descriptor) {
		this.setNegateExpression(descriptor.isNegateExpression());
		this.setPieAttribute(descriptor.getPieAttribute());
	}

	@Override
	public PieDescriptorExpression getCopy() {
		return new PieDescriptor(this);
	}

	@Override
	public boolean eval(Pie pie) {

		if (pieAttribute instanceof Pie.Shapes) {
			return negateExpression ? !pieAttribute.equals(pie.getShape()) : pieAttribute.equals(pie.getShape());
		} else if (pieAttribute instanceof Pie.CrustSizes) {
			return negateExpression ? !pieAttribute.equals(pie.getCrustSize())
					: pieAttribute.equals(pie.getCrustSize());
		} else if (pieAttribute instanceof Pie.CrustShades) {
			return negateExpression ? !pieAttribute.equals(pie.getCrustShade())
					: pieAttribute.equals(pie.getCrustShade());
		} else if (pieAttribute instanceof Pie.FillingSizes) {
			return negateExpression ? !pieAttribute.equals(pie.getFillingSize())
					: pieAttribute.equals(pie.getFillingSize());
		} else if (pieAttribute instanceof Pie.FillingShades) {
			return negateExpression ? !pieAttribute.equals(pie.getFillingShade())
					: pieAttribute.equals(pie.getFillingShade());
		}

		return false;
	}

	public boolean isNegateExpression() {
		return negateExpression;
	}

	public void setNegateExpression(boolean negateExpression) {
		this.negateExpression = negateExpression;
	}

	public PieAttribute getPieAttribute() {
		return pieAttribute;
	}

	public void setPieAttribute(PieAttribute pieAttribute) {
		this.pieAttribute = pieAttribute;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (obj instanceof PieDescriptor) {
			PieDescriptor other = (PieDescriptor) obj;
			return this.isNegateExpression() == other.isNegateExpression()
					&& this.getPieAttribute().equals(other.getPieAttribute());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.getPieAttribute().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.isNegateExpression()) {
			sb.append(" NOT(").append(this.getPieAttribute().toString()).append(") ");
		} else {
			sb.append(" ").append(this.getPieAttribute().toString()).append(" ");
		}
		return sb.toString();
	}
}
