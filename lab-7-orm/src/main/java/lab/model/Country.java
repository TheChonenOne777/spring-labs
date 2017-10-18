package lab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String codeName;
}
