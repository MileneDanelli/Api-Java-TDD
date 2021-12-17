import React from 'react';

const TableContato = props => (
  
    <table className="responsive-table striped">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Qtd</th>
                <th>Compra</th>
                <th>Venda</th>
                <th>Fornecedor</th>
                <th>Tipo</th>
            </tr>
        </thead>
    <tbody>
        {
            props.contatos && props.contatos.length > 0 ? (
                props.contatos.map (contato => (

                    <tr key={contato.id}>
                        <td>{contato.nome}</td>
                        <td>{contato.qtd}</td>
                        <td>{contato.compra}</td>
                        <td>{contato.venda}</td>
                        <td>{contato.fornecedorNome}</td>
                        <td>{contato.tipoNome}</td>
                        <td className="center-align">
                            <button 
                                className="waves-effect waves-light btn-small cyan lighten-2"
                                onClick={() => props.editRow(contato)}>
                                Editar
                            </button>

                            <button 
                                className="waves-effect waves-light btn-small red pink accent-1"
                                onClick={() => props.deleteContato(contato.id)}>
                                Deletar
                            </button>
                        </td> 
                    </tr>
                    ))
                ) : (
                    <tr>
                        <td colSpan={3}>{props.contatos && props.contatos[0]} Não há Produtos.</td>
                    </tr>
                )
        }          
    </tbody>
  </table>
);
    
export default TableContato;