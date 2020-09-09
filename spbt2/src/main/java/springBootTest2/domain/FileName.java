package springBootTest2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileName {
	String originalFileName;
	String storeFileName;
	String fileSize;
}
