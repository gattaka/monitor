package cz.gattserver.monitor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MonitorController {

	@Value("${scripts.path}")
	private String scriptsPath;

	@Value("${disk.mount.script}")
	private String diskMountScript;

	@Value("${last.backup.script}")
	private String lastBackupScript;

	@Value("${services.status.script}")
	private String servicesStatusScript;

	@Value("${swap.status.script}")
	private String swapStatusScript;

	@Value("${jmap.list.script}")
	private String jmapListScript;

	@Value("${memory.status.script}")
	private String memoryStatusScript;

	@Value("${smart.status.script}")
	private String smartStatusScript;

	@Value("${backup.disk.mount.script}")
	private String backupDiskMountScript;

	@GetMapping("/uptime")
	String uptime() throws IOException, InterruptedException {
		return Console.executeCommand("uptime");
	}

	@GetMapping("/disk-mounts")
	String diskMounts() throws IOException, InterruptedException {
		return Console.executeCommand(scriptsPath + diskMountScript);
	}

	@GetMapping("/last-backup")
	String lastBackup() throws IOException, InterruptedException {
		return Console.executeCommand(scriptsPath + lastBackupScript);
	}

	@GetMapping("/services-status")
	String servicesStatus() throws IOException, InterruptedException {
		return Console.executeCommand(scriptsPath + servicesStatusScript);
	}

	@GetMapping("/swap-status")
	String swapStatus() throws IOException, InterruptedException {
		return Console.executeCommand(scriptsPath + swapStatusScript);
	}

	@GetMapping("/jmap-list")
	String jmapList() throws IOException, InterruptedException {
		return Console.executeCommand(scriptsPath + jmapListScript);
	}

	@GetMapping("/memory-status")
	String memoryStatus() throws IOException, InterruptedException {
		return Console.executeCommand(scriptsPath + memoryStatusScript);
	}

	@GetMapping("/smart-status")
	String smartStatus() throws IOException, InterruptedException {
		return Console.executeCommand(scriptsPath + smartStatusScript);
	}

	@GetMapping("/backup-disk-mount")
	String backupDiskMount() throws IOException, InterruptedException {
		return Console.executeCommand(scriptsPath + backupDiskMountScript);
	}


}
