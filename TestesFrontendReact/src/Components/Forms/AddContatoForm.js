import React, { useState } from 'react';

const AddContatoForm = props => {
    const initialFormState = {  
        nome: '', 
        qtd: 0, 
        compra: 0.0, 
        venda: 0.0, 
        fornecedor: {
            nome: '',
            telefone: ''
        },
        tipo: {
            nome: ''
        } 
    };
    const [contato, setContato] = useState(initialFormState);

    const handleInputChange = event => {
        const {name, value} = event.target;
        setContato({ ...contato, [name]: value });
    }

    const submitForm = event => {
        event.preventDefault();
        if (!contato.nome || !contato.qtd || !contato.compra || !contato.venda || !contato.fornecedor || !contato.tipo) return;
        props.addContato(contato);
        setContato(initialFormState);
    };

    return (
        <div className="row">
            <form className="col s12"
                onSubmit={submitForm}>
               <div className="row">
                    <div className="input-field col s12">
                        <input type="text" 
                            id={contato.id} 
                            name="nome"
                            value={contato.nome}
                            onChange={handleInputChange} 
                            required />
                        <label htmlFor="nome">Nome:</label>
                    </div>
                </div>

                <div className="row">
                    <div className="input-field col s12">
                        <input 
                            type="text" 
                            name="qtd" 
                            value={contato.qtd}
                            onChange={handleInputChange} 
                            required />
                        <label htmlFor="qtd">Qtd:</label>
                    </div>
                </div>

                <div className="row">
                    <div className="input-field col s12">
                        <input 
                            type="text" 
                            name="compra" 
                            value={contato.compra}
                            onChange={handleInputChange} 
                            required />
                        <label htmlFor="compra">Compra:</label>
                    </div>
                </div>

                <div className="row">
                    <div className="input-field col s12">
                        <input 
                            type="text" 
                            name="venda" 
                            value={contato.venda}
                            onChange={handleInputChange} 
                            required />
                        <label htmlFor="venda">Venda:</label>
                    </div>
                </div>

                <div className="row">
                    <div className="input-field col s12">
                        <input 
                            type="text" 
                            name="fornecedor" 
                            value={contato.fornecedor}
                            onChange={handleInputChange} 
                            required />
                        <label htmlFor="fornecedor">Fornecedor:</label>
                    </div>
                </div>

                <div className="row">
                    <div className="input-field col s12">
                        <input 
                            type="text" 
                            name="tipo" 
                            value={contato.tipo}
                            onChange={handleInputChange} 
                            required />
                        <label htmlFor="tipo">Tipo:</label>
                    </div>
                </div>
                
                <div className="row">
                    <div className="input-field col s12">
                        <button id="btn-cadastrar" className="waves-effect waves-light btn cyan lighten-2">Cadastrar</button>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default AddContatoForm;