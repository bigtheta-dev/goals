import React, {Component} from 'react';
import Task from './Task';

class TaskList extends Component {
    render() {
        return (
            <div className="flex-container">
                {this.props.tasks.map((task) => <Task key={task.id} {...task}/>)}
            </div>
        );
    }
}

export default TaskList;
