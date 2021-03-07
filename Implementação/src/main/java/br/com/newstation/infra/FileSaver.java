package br.com.newstation.infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Part;

public class FileSaver {

	public static final String SERVER_PATH = "/ImagensLes";
	
	public String write(Part arquivo, String path){
		String relativePath =  path +"/" + arquivo.getSubmittedFileName();
		
		if(Paths.get(SERVER_PATH + "/"  + relativePath) != null) {
			try {
				Files.delete(Paths.get(SERVER_PATH + "/"  + relativePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			
			arquivo.write(SERVER_PATH + "/"  + relativePath);
			
			return relativePath;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	public static void transfer(Path source, ServletOutputStream outputStream) {
		try {
			FileInputStream input = new FileInputStream(source.toFile());
			try( ReadableByteChannel inputChannel = Channels.newChannel(input); WritableByteChannel outputChannel = Channels.newChannel(outputStream)) {
				
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
				
				while(inputChannel.read(buffer) != -1) {//-1 significa que não tem mais nada para ser lido
					buffer.flip();//coloca bytes em 0
					outputChannel.write(buffer);
					buffer.clear();//limpa para outra operação
				}
			} catch (IOException e) {
				throw new RuntimeException(e); 
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e); 
		}
		
	}
}
