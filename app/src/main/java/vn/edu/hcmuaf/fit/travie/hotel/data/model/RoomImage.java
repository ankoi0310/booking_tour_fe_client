package vn.edu.hcmuaf.fit.travie.hotel.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.hcmuaf.fit.travie.core.domain.BaseModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomImage extends BaseModel {
    private String image;
    private RoomType roomType;
}
