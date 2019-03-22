package pt.ipp.isep.dei.project.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class LocationDTO {
    private double latitude;
    private double longitude;
    private double elevation;
}
