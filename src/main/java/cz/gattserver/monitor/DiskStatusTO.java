package cz.gattserver.monitor;

public class DiskStatusTO {

	// Souborový systém
	private String fs;

	// Velikost
	private String size;

	// Užito
	private String used;

	// Volno
	private String free;

	// Uži%
	private String usedPercent;

	// Připojeno do
	private String mountPoint;

	// FS typ
	private String fsType;

	// Mount options
	private String mountOptions;

	public String getFs() {
		return fs;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getUsedPercent() {
		return usedPercent;
	}

	public void setUsedPercent(String usedPercent) {
		this.usedPercent = usedPercent;
	}

	public String getMountPoint() {
		return mountPoint;
	}

	public void setMountPoint(String mountPoint) {
		this.mountPoint = mountPoint;
	}

	public String getFsType() {
		return fsType;
	}

	public void setFsType(String fsType) {
		this.fsType = fsType;
	}

	public String getMountOptions() {
		return mountOptions;
	}

	public void setMountOptions(String mountOptions) {
		this.mountOptions = mountOptions;
	}
}
