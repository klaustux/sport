package lt.eimis.util;

public class SportPosition {
	private int positionId;
	private String positionName;

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public SportPosition() {
	}

	public SportPosition(int positionId, String positionName) {
		this.positionId = positionId;
		this.positionName = positionName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		SportPosition that = (SportPosition) o;

		if (positionId != that.positionId) {
			return false;
		}
		return positionName != null ? positionName.equals(that.positionName) : that.positionName == null;

	}

	@Override
	public int hashCode() {
		int result = positionId;
		result = 31 * result + (positionName != null ? positionName.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "SportPosition{" +
				"positionId=" + positionId +
				", positionName='" + positionName + '\'' +
				'}';
	}
}
