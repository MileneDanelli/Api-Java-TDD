import React, { useState, useEffect } from 'react';

const EditContatoForm = props => {
    const [contato, setContato] = useState(props.currentContato);

    const handleInputChange = event => {
        const { name, value } = event.target
        setContato({ ...contato, [name]: value })
    };

    const submitForm = event => {
        event.preventDefault();
        props.updateContato(contato.id, contato);
    };

    useEffect(() => {
        setContato(props.currentContato);
    }, [props]);

    return (
        <div className="row">
            <form className="col s12"
                onSubmit={submitForm}>
                <div className="row">
                    <div className="col s12">
                    <label htmlFor="nome">Nome:</label>
                        <input type="text" 
                            id={contato.id} 
                            name="nome"
                            value={contato.nome}
                            onChange={handleInputChange} 
                            required />
                    </div>
                </div>

                <div className="row">
                    <div className="col s12">
                        <label htmlFor="qtd">Qtd:</label>
                        <input 
                            type="text" 
                            name="qtd" 
                            value={contato.qtd}
                            onChange={handleInputChange} 
                            required />
                    </div>
                </div>

                <div className="row">
                    <div className="col s12">
                        <label htmlFor="compra">Compra:</label>
                        <input 
                            type="text" 
                            name="compra" 
                            value={contato.compra}
                            onChange={handleInputChange} 
                            required />
                    </div>
                </div>

                <div className="row">
                    <div className="col s12">
                        <label htmlFor="venda">Venda:</label>
                        <input 
                            type="text" 
                            name="venda" 
                            value={contato.venda}
                            onChange={handleInputChange} 
                            required />
                    </div>
                </div>

                <div className="row">
                    <div className="col s12">
                        <label htmlFor="fornecedor">Fornecedor:</label>
                        <input 
                            type="text" 
                            name="fornecedor" 
                            value={contato.fornecedor}
                            onChange={handleInputChange} 
                            required />
                    </div>
                </div>

                <div className="row">
                    <div className="col s12">
                        <label htmlFor="tipo">Tipo:</label>
                        <input 
                            type="text" 
                            name="tipo" 
                            value={contato.tipo}
                            onChange={handleInputChange} 
                            required />
                    </div>
                </div>
                
                <div className="row">
                    <div className="input-field col s12 m6">
                        <button className="waves-effect waves-light btn cyan lighten-2">Editar</button>
                    </div>
                    <div className="input-field col s12 m6">
                        <button 
                            className="waves-effect waves-light btn cyan lighten-2"
                            onClick={() => props.setEditing(false)}
                        >
                            Cancelar
                        </button>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default EditContatoForm;