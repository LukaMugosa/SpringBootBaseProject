package me.amplitudo.stanley.web.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class JsonErrorResponseVM {

    private String code;

    private String message;


}
