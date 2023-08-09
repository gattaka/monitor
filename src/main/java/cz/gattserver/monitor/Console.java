package cz.gattserver.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Console {

	private static Logger logger = LoggerFactory.getLogger(Console.class);

	public static String executeCommand(String command) throws IOException, InterruptedException {
		return executeCommand(Arrays.asList(command));
	}

	public static String executeCommand(List<String> commandAndArguments) throws IOException, InterruptedException {
		Path dummyInput = null;
		dummyInput = Files.createTempFile(UUID.randomUUID().toString(), "GRASS-CONSOLE-DUMMY-INPUT");
		try {
			ProcessBuilder pb = new ProcessBuilder(commandAndArguments);

			// staré API... toFile :( Ale tohle se stejně nedá testovat,
			// protože OS-specific
			pb.redirectInput(dummyInput.toFile());
			Process process = pb.start();
			int returned = process.waitFor();
			if (returned != 0)
				throw new IllegalStateException(
						"Vrácená hodnota prováděného příkazu je '" + returned + "', namísto očekávané '0'");
			try (BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				return buffer.lines().collect(Collectors.joining("\n"));
			}
		} finally {
			try {
				Files.delete(dummyInput);
			} catch (IOException e) {
				logger.error("Nezdařilo se smazat dočasný soubor logu console", e);
			}
		}
	}
}