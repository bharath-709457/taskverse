const DashboardLayout = ({ children }) => {
  return (
    <div className="min-h-screen flex flex-col">
      <header className="bg-blue-600 text-white p-4 text-xl font-bold">
        TaskVerse Dashboard
      </header>
      <main className="flex-grow p-4 bg-gray-100">{children}</main>
      <footer className="bg-gray-200 text-center p-2 text-sm">
        © 2025 TaskVerse
      </footer>
    </div>
  );
};

export default DashboardLayout;