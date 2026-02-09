<template>
  <div class="container">
    <h1>Todo App (Spring Boot + Vue)</h1>

    <form @submit.prevent="createTask">
      <input v-model="newTask.title" placeholder="Title" required />
      <input v-model="newTask.description" placeholder="Description" />
      <button>Add Task</button>
    </form>

    <ul>
      <li v-for="task in tasks" :key="task.id">
        <input type="checkbox" v-model="task.completed" @change="updateTask(task)" />
        <span :style="{ textDecoration: task.completed ? 'line-through' : 'none' }">
          {{ task.title }} - {{ task.description }}
        </span>
        <button @click="deleteTask(task.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'

const API_URL = 'http://localhost:8080/api/tasks'

export default {
  data() {
    return {
      tasks: [],
      newTask: {
        title: '',
        description: '',
        completed: false
      }
    }
  },
  mounted() {
    this.fetchTasks()
  },
  methods: {
    async fetchTasks() {
      const res = await axios.get(API_URL)
      this.tasks = res.data
    },
    async createTask() {
      const res = await axios.post(API_URL, this.newTask)
      this.tasks.push(res.data)
      this.newTask.title = ''
      this.newTask.description = ''
    },
    async updateTask(task) {
      await axios.put(`${API_URL}/${task.id}`, task)
    },
    async deleteTask(id) {
      await axios.delete(`${API_URL}/${id}`)
      this.tasks = this.tasks.filter(t => t.id !== id)
    }
  }
}
</script>

<style>
.container {
  max-width: 600px;
  margin: 40px auto;
  font-family: Arial, sans-serif;
}

form {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

input {
  padding: 6px;
}

button {
  padding: 6px 10px;
  cursor: pointer;
}

li {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 6px 0;
}
</style>
