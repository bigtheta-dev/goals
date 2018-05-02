import './TaskForm.css';
import React, {Component} from 'react';
import axios from "axios";

import {Button, FormControl, FormGroup} from 'react-bootstrap';

class TaskForm extends Component {

    state = {
        task: '',
        filter: ''
    };

    componentDidMount() {
        this.state.filter.focus();
    }

    handleFilter = (event) => {
        event.preventDefault();
        const value = event.target.value;
        this.props.updateFilter(value);
    };

    handleCreate = (event) => {
        event.preventDefault();
        axios.post(`http://web:8080/api/task`, {
                'value': `${this.state.task}`
            },
        ).then(ignore =>
            axios.get(`http://web:8080/api/task/`)
                .then(resp => {
                    this.props.updateTasks(resp.data);
                    this.setState({task: ''});
                })
        );

    };

    render() {
        return (<div className="Forms">
                <form autoComplete="off">
                    <FormGroup className="form-container" controlId="formFilter">

                        <FormControl className="form-item-input"
                                     inputRef={ref => {
                                         this.state.filter = ref;
                                     }}
                                     type="text"
                                     placeholder="filter"
                                     onChange={this.handleFilter}
                        />

                    </FormGroup>

                </form>
                <form autoComplete="off" onSubmit={this.handleCreate}>


                    <FormGroup className="form-container" controlId="formCreate">
                        <FormControl className="form-item-input"
                                     value={this.state.task}
                                     onChange={(event) => this.setState({task: event.target.value})}
                                     type="text"
                                     placeholder="new value"
                        />
                        <Button className="form-item-button" bsStyle="primary" type="submit">✚</Button>
                    </FormGroup>

                </form>

            </div>
        );
    }
}

export default TaskForm;
