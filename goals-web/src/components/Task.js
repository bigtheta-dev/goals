import './Task.css';
import React, {Component} from 'react';
import axios from "axios";
import {Button} from 'react-bootstrap';

class Task extends Component {
    state = {
        display: 'block'
    };
    handleClick = () => {
        axios.delete(`http://139.59.208.233/api/task/${this.props.id}`)
            .catch(function (error) {
                console.log(error);
            });
        this.setState({display: 'none'});
        console.log(this.props.id);
    };

    render() {
        return <div className="item" style={{display: `${this.state.display}`}}>
            <div className="task">
                <div className="taskId">
                    {this.props.id}
                </div>
                <div className="taskValue">
                    {this.props.value}
                </div>
                <div className="taskStatus">
                    {this.props.status}
                </div>
                <Button className="taskButton" bsStyle="success" onClick={this.handleClick}>✖</Button>
            </div>
        </div>
    }
}

export default Task;
