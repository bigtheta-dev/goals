import React, {Component} from 'react';
import TaskList from './Tasks';
import TaskForm from './TaskForm';

import axios from "axios";
import './App.css';


class App extends Component {
    state = {
        filter: '',
        tasks: []
    };

    componentDidMount() {
        axios.get(`http://web:8080/api/task/`)
            .then(resp => {
                this.setState(() => ({
                    tasks: resp.data
                }));
            });
    }

    updateTasks = (input) => {
        this.setState(() => ({
            tasks: input
        }));
    };

    updateFilter = (input) => {
        this.setState(() => ({
            filter: input
        }));
    };


    render() {
        return (
            <div className="App">
                <TaskForm updateFilter={this.updateFilter} updateTasks={this.updateTasks}/>
                <TaskList tasks={this.state.tasks.filter(task => task.value.includes(this.state.filter))}/>
            </div>
        );
    }
}


export default App;
