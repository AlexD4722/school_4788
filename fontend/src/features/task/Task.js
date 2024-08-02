import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import {
  addTask,
  selectTask
} from './taskSlice';

export function Task() {
  const taskData = useSelector(selectTask);
  const dispatch = useDispatch();
  const [taskName, setTaskName] = useState("");

  return (
    <div>
      <h1 className=''>lorem</h1>
      <input
        value={taskName}
        onChange={(e) => setTaskName(e.target.value)}
      />

      <button
        onClick={() => dispatch(addTask(taskName))}
      >
        Add Task
      </button>

      <ul>
        {taskData.tasks.map((task, index) => (
          <li key={index}>{task}</li>
        )
        )}
      </ul>
    </div>
  );
}