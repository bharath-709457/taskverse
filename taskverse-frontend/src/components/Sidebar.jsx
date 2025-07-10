import { Link } from "react-router-dom";

const Sidebar = () => {
  return (
    <aside className="w-64 bg-white shadow-lg">
      <div className="p-4 text-xl font-bold border-b">TaskVerse</div>
      <nav className="flex flex-col p-4 space-y-3">
        <Link to="/dashboard" className="text-gray-700 hover:text-blue-600">Dashboard</Link>
        <Link to="/projects" className="text-gray-700 hover:text-blue-600">Projects</Link>
        <Link to="/tasks" className="text-gray-700 hover:text-blue-600">Tasks</Link>
        <Link to="/teams" className="text-gray-700 hover:text-blue-600">Teams</Link>
      </nav>
    </aside>
  );
};

export default Sidebar;
