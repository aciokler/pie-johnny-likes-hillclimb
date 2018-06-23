package piejohnnylikes;

import algorithm.hillclimbsearch.ClassifierState;

public class PieClassifier implements ClassifierState {

	private PieDescriptorExpression descriptor;
	private double errorRate = -1.0;

	public PieClassifier(PieDescriptorExpression descriptor) {
		this.descriptor = descriptor;
	}

	public PieClassifier(double desiredErrorRate) {
		this.errorRate = desiredErrorRate;
	}

	public PieClassifier(PieClassifier classifierToCopy) {
		this(classifierToCopy.getDescriptor().getCopy());
	}

	public PieDescriptorExpression getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(PieDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public double getErrorRate() {
		return errorRate;
	}

	public void setErrorRate(double errorRate) {
		this.errorRate = errorRate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (obj instanceof PieClassifier) {
			PieClassifier classifier = (PieClassifier) obj;
			return (this.getErrorRate() < 0.0 && this.getDescriptor().equals(classifier.getDescriptor()))
					|| (this.getErrorRate() >= 0.0 && this.getErrorRate() <= classifier.getErrorRate());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.getDescriptor().hashCode();
	}
}
