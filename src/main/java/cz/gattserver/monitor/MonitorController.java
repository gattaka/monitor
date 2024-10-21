package cz.gattserver.monitor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class MonitorController {

	@Value("${scripts.path}")
	private String scriptsPath;

	// TODO servers

	@Value("${disk.mount.script}")
	private String diskMountScript;
	@Value("${disk.free.script}")
	private String diskFreeScript;

	@Value("${last.backup.script}")
	private String lastBackupScript;

	// Systemctl services
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

	@GetMapping("/disks")
	String diskMounts() throws IOException, InterruptedException {
		// /dev/sda3 on / type ext3 (rw,relatime)
		// /dev/sdb1 on /ftp/Filmy type ext4 (rw," + "relatime)
		// /dev/sda2 on /boot type ext2 (rw,relatime,stripe=4)
		// /dev/sdc1 on /mnt/backup type ext4 (rw,relatime)
		String mounts = Console.executeCommand(scriptsPath + diskMountScript);

		// Souborový systém Velikost Užito Volno Uži% Připojeno do
		// dev                  7,8G     0  7,8G   0% /dev
		// run                  7,8G  1,4M  7,8G   1% /run
		// /dev/sda3            915G  413G  456G  48% /
		// tmpfs                7,8G     0  7,8G   0% /dev/shm
		// tmpfs                7,8G   32K  7,8G   1% /tmp
		// tmpfs                7,8G  2,8M  7,8G   1% /etc/pacman.d/gnupg
		// /dev/sdb1            1,8T  1,3T  491G  72% /ftp/Filmy
		// /dev/sda2            193M  170M   13M  94% /boot
		// tmpfs                1,6G   28K  1,6G   1% /run/user/1000
		// /dev/sdc1            1,8T  1,6T  135G  93% /mnt/backup
		String df = Console.executeCommand(scriptsPath + diskFreeScript);

		Set<String> mountedSet = Arrays.stream(mounts.split("\n")).map(line -> line.split(" ")[0])
				.collect(Collectors.toSet());

		return Arrays.stream(df.split("\n")).filter(line -> mountedSet.contains(line.split(" ")[0]))
				.collect(Collectors.joining("\n"));
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