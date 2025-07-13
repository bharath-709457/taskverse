import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import AddTaskModal from "../components/AddTaskModal"; // import modal

const Dashboard = () => {
  const [tasks, setTasks] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const navigate = useNavigate();

const fetchTasks = async () => {
  const token = localStorage.getItem("token");
  if (!token) return navigate("/login");

  try {
    const response = await fetch("http://localhost:8080/api/tasks", {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json",
      },
      mode: "cors",
    });
    console.log(token);
    if (response.status === 403) {
      throw new Error("Forbidden: Your session may have expired.");
    }

    if (!response.ok) throw new Error("Failed to fetch tasks");

    const data = await response.json();
    setTasks(data);
  } catch (err) {
    console.error("TASK FETCH ERROR:", err);
    alert("Session expired or unauthorized");
    navigate("/login");
  }
};


  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  const handleAddTask = (newTask) => {
    setTasks((prev) => [newTask, ...prev]);
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  return (
    <div className="flex min-h-screen">
      {/* Sidebar */}
      <div className="w-64 bg-gray-800 text-white p-4">
        <h2 className="text-2xl font-bold mb-6">TaskVerse</h2>
        <button
          onClick={handleLogout}
          className="w-full bg-red-500 py-2 mt-4 rounded hover:bg-red-600"
        >
          Logout
        </button>
      </div>

      {/* Main */}
      <div className="flex-1 p-6 bg-gray-50">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-3xl font-bold">Dashboard</h1>
          <button
            onClick={() => setShowModal(true)}
            className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
          >
            + Add Task
          </button>
        </div>

        {tasks.length > 0 ? (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            {tasks.map((task) => (
              <div key={task.id} className="bg-white p-4 shadow rounded">
                <h3 className="text-lg font-semibold">{task.title}</h3>
                <p className="text-gray-600">{task.description}</p>
                <p className="text-sm mt-2">
                  Status:{" "}
                  <span
                    className={
                      task.completed
                        ? "text-green-600 font-medium"
                        : "text-yellow-600 font-medium"
                    }
                  >
                    {task.completed ? "Completed" : "Pending"}
                  </span>
                </p>
              </div>
            ))}
          </div>
        ) : (
          <p>No tasks available.</p>
        )}
      </div>

      {/* Show modal */}
      {showModal && (
        <AddTaskModal onClose={() => setShowModal(false)} onAdd={handleAddTask} />
      )}
    </div>
  );
};

export default Dashboard;
