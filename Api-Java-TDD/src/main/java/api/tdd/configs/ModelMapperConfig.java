package api.tdd.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
//        modelMapper.createTypeMap(Produto.class, ProdutoDTO.class)
//                .<TipoProduto>addMapping(src -> src.getTipoProduto(),
//                        (dest, value) -> dest.setTipoProduto(value));
        return modelMapper;
    }

}

