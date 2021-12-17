import React, { Component } from 'react';
import qs from 'querystring';

import api from '../api';

import TableContato from './TableContato';
import AddContatoForm from './Forms/AddContatoForm';
import EditContatoForm from './Forms/EditContatoForm';

class Lista extends Component {
    constructor(props) {
        super(props);
        this.state = {
            contatos: [],
            currentContato: { 
                id: null, 
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
            },
            editing: false
        }
    }

    componentDidMount() {
        this.refreshContatoTable();
    }

    refreshContatoTable() {
        this.contatosData = api.get('produtos')
            .then(response => response.data)
            .then(data => {
                this.setState({ 
                    contatos: data,
                    setContatos: data,
                });
            });
    }

    addContato = contato => {
        api.post('produtos', qs.stringify(contato))
            .then(res => {
                this.refreshContatoTable();
            });
    };

    deleteContato = id => {
        api.delete(`produtos/${id}`)
            .then(res => {
                this.refreshContatoTable();
            });
    };

    updateContato = (id, contato) => {
        api.put(`produtos/${id}`, qs.stringify(contato))
            .then(res => {
                this.refreshContatoTable();
            });
        this.setState({ 
            currentContato: { 
                id: null, 
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
            }
        });
        this.setEditing(false);
    };

    editRow = contato => {
        this.setState({ 
            currentContato: { id: contato.id, 
                nome: contato.nome, 
                qtd: contato.qtd, 
                compra: contato.compra, 
                venda: contato.venda, 
                fornecedor: {
                    nome: contato.fornecedorNome,
                    telefone: contato.fornecedorTelefone
                },
                tipo: {
                    nome:  contato.tipoNome
                }  
            }
        });
        this.setEditing(true);
    };

    setEditing = isEditing => {
        this.setState({ editing: isEditing });
    };

    render () {
        const { contatos } = this.state;
        return (
            <div className="container">
                <h3 id="title" style={{ marginLeft: '10px' }}>Produtos</h3>
                <div className="row">
                    {
                        this.state.editing ? (
                            <div className="col s12 l6">
                                <h4>Editar Produto</h4>
                                <EditContatoForm 
                                    editing={this.state.editing}
                                    setEditing={this.setEditing}
                                    currentContato={this.state.currentContato}
                                    updateContato={this.updateContato} 
                                />
                            </div>
                        ) : (
                            <div className="col s12 l6">
                                <h4>Cadastrar Produto</h4>
                                <AddContatoForm addContato={this.addContato} />
                            </div>
                        )
                    }
                    
                    <div className="col s12 l6">
                        <h5>Lista</h5>
                        <TableContato contatos={contatos} editRow={this.editRow} deleteContato={this.deleteContato} />
                    </div>
                </div>
            </div>
        );
    };
};

export default Lista;
